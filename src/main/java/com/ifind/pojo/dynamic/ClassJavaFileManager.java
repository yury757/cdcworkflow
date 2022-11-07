package com.ifind.pojo.dynamic;

import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * JVM字节码文件管理器，用于生成JVM字节码文件对象，并提供编译好的字节码文件对象
 * @author yury
 */
public class ClassJavaFileManager extends ForwardingJavaFileManager<JavaFileManager> {
    private final List<ByteArrayFileObject> classFileList = new ArrayList<>();

    public ClassJavaFileManager(JavaFileManager fileManager) {
        super(fileManager);
    }

    /**
     * 获取用于输出的 JavaFileObject 对象
     * classFileList.add(byteArrayJavaClass); 是用于
     * @param location
     * @param className
     * @param kind
     * @param sibling
     * @return
     * @throws IOException
     */
    @Override
    public JavaFileObject getJavaFileForOutput(Location location, String className, JavaFileObject.Kind kind, FileObject sibling) throws IOException {
        ByteArrayFileObject byteArrayFileObject = new ByteArrayFileObject(className);
        classFileList.add(byteArrayFileObject);
        return byteArrayFileObject;
    }

    public List<ByteArrayFileObject> getClassFileList() {
        return classFileList;
    }
}
