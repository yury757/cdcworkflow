package net.yury.core.dynamic;

import javax.tools.SimpleJavaFileObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

/**
 * 存放内存的字节码文件对象
 * @author yury
 */
public class ByteArrayFileObject extends SimpleJavaFileObject {
    // 字节数组输出流
    private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    protected ByteArrayFileObject(String name) {
        super(URI.create("bytes:///" + name), Kind.CLASS);
    }

    /**
     * 编译完成后会回调该方法获取输出流，将字节码通过该输出流输出
     * 这里直接返回 byteArrayOutputStream 私有属性，就可以将字节码输出到输出流内部的字节数组中
     * @return
     * @throws IOException
     */
    @Override
    public OutputStream openOutputStream() throws IOException {
        return byteArrayOutputStream;
    }

    /**
     * 获取编译好的字节码
     * @return
     */
    public byte[] getCompiledBytes() {
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 重写该方法，原始的方法，获取 name 会带 "/"
     * @return
     */
    @Override
    public String getName() {
        return toUri().getPath().replace("/", "");
    }
}

