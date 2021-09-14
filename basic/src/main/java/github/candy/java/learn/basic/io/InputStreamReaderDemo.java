package github.candy.java.learn.basic.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import github.candy.java.learn.basic.config.Constants;

public class InputStreamReaderDemo {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(new FileInputStream(Constants.PATH),"utf-8");
        int len;
        while ((len = reader.read()) != -1) {
            System.out.print((char) len);
        }
        reader.close();
    }
}
