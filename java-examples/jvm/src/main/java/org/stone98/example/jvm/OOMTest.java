package org.stone98.example.jvm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OOMTest {
    
    public static void main(String[] args) {
        List<Picture> list = new ArrayList<Picture>();
        while (true) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new Picture(new Random().nextInt(100 * 50)));
        }
    }
}

class Picture {
    
    private byte[] pixels;
    
    public Picture(int length) {
        this.pixels = new byte[length];
    }
}
