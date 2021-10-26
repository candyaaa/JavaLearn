package github.stone98.example.java.basic.io;

import github.stone98.example.java.basic.config.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class OutputStreamWriterDemo {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(new File(Constants.PATH));
        OutputStreamWriter os = new OutputStreamWriter(fileOutputStream, "utf-8");
        String str = "你好嘛？";
        os.write(str);
        os.flush();
        os.close();
    }
}
