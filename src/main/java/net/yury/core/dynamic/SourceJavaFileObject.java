package net.yury.core.dynamic;

import javax.tools.SimpleJavaFileObject;
import java.io.IOException;
import java.net.URI;

/**
 * 存放内存的java源代码文件对象
 * @author yury
 */
public class SourceJavaFileObject extends SimpleJavaFileObject {
    private String source;

    public SourceJavaFileObject(String name, String source) {
        super(URI.create("string:///" + name.replace(".", "/") + Kind.SOURCE.extension), Kind.SOURCE);
        this.source = source;
    }

    @Override
    public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
        return source;
    }
}
