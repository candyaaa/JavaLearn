package github.candy.java.learn.basic.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import github.candy.java.learn.basic.config.Constants;

public class FileInputStreamDemo {
    public static void main(String[] args) throws IOException {
        InputStream inputStream = new FileInputStream(new File(Constants.PATH));
        int i;
        while ((i = inputStream.read()) != -1) {
            System.out.print((char) i + " ");
        }
        inputStream.close();
    }
}
