package github.candy.java.learn.basic.io;

import java.io.*;
import github.candy.java.learn.basic.config.Constants;

public class OutputStreamWriterDemo {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(Constants.PATH));
        OutputStreamWriter os = new OutputStreamWriter(fileOutputStream ,"utf-8");
        String str = "你好嘛？";
        os.write(str);
        os.flush();
        os.close();
    }
}
