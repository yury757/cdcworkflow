package com.ifind.core.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.tools.*;
import java.lang.reflect.Constructor;
import java.util.*;

/**
 * 动态编译工具类
 * @author yury
 */
public class CompileUtil {
    private static final Logger LOG = LoggerFactory.getLogger(CompileUtil.class);

    /**
     * 以字符串的形式动态编译java代码，并将生成的对象注册到容器中
     * @param simpleName 生成的字节码文件名，一定要是类名简称，如 HelloService，否则会编译不通过
     * @param source java源代码
     */
    public static void compile(String simpleName, String source) {
        compile(Collections.singletonList(new SourceJavaFileObject(simpleName, source)));
    }

    /**
     * 批量编译
     * @param sourceMap
     */
    public static void compileBatch(Map<String, String> sourceMap) {
        List<SourceJavaFileObject> sourceJavaFileObjectList = new ArrayList<>();
        for (Map.Entry<String, String> entry : sourceMap.entrySet()) {
            sourceJavaFileObjectList.add(new SourceJavaFileObject(entry.getKey(), entry.getValue()));
        }
        compile(sourceJavaFileObjectList);
    }

    /**
     * 编译
     * @param sourceJavaFileObjectList
     */
    private static void compile(List<SourceJavaFileObject> sourceJavaFileObjectList) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        try (StandardJavaFileManager standardFileManager = compiler.getStandardFileManager(diagnostics, null, null);
             ClassJavaFileManager fileManager = new ClassJavaFileManager(standardFileManager)) {
            Boolean result = compiler.getTask(null, fileManager, diagnostics, null, null, sourceJavaFileObjectList).call();
            for (Diagnostic<? extends JavaFileObject> diagnostic : diagnostics.getDiagnostics()) {
                LOG.info("{}, {}, {}", diagnostic.getCode(), diagnostic.getLineNumber(), diagnostic.getMessage(Locale.SIMPLIFIED_CHINESE));
            }
            if (!result) {
                LOG.error("compile failed");
                return;
            }
            // 字节码文件对象列表
            List<ByteArrayFileObject> classFileList = fileManager.getClassFileList();
            for (ByteArrayFileObject byteArrayFileObject : classFileList) {
                byte[] compiledBytes = byteArrayFileObject.getCompiledBytes();
                String fullName = byteArrayFileObject.getName();
                MapClassLoader.INSTANCE.put(fullName, compiledBytes);
                Class<?> clazz = MapClassLoader.INSTANCE.loadClass(fullName);
                Constructor<?> constructor = clazz.getConstructor();
                constructor.setAccessible(true);
                Object o = constructor.newInstance();
                int lastIndex = fullName.lastIndexOf("\\.");
                String simpleName = fullName.substring(lastIndex);
                DynamicObjectContainer.INSTANCE.register(simpleName, o);
            }
        }catch (Throwable t) {
            LOG.error("compile failed", t);
        }
    }
}
