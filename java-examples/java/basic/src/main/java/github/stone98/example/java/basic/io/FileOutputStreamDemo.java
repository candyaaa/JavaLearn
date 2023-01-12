package github.stone98.example.java.basic.io;

import github.stone98.example.java.basic.config.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FileOutputStreamDemo {
    public static void main(String[] args) throws IOException {
        OutputStream outputStream = new FileOutputStream(new File(Constants.PATH));
        String str = "a";
        outputStream.write(str.getBytes());
        outputStream.close();

        // 内容追加写入
        OutputStream outputStream2 = new FileOutputStream(Constants.PATH, true);
        String str2 = "hello";
        outputStream2.write("\r\n".getBytes());
        outputStream2.write(str2.getBytes());
        outputStream2.close();
    }
}
