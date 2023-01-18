package org.stone98.example.jvm;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class PermGenOomMock {
    public static void main(String[] args) {
        URL url;
        List<ClassLoader> classLoaderList = new ArrayList<>();
        try {
            url = new File("/tmp").toURI().toURL();
            URL[] urls = {url};
            while (true){
                ClassLoader loader = new URLClassLoader(urls);
                classLoaderList.add(loader);
                loader.loadClass("org.stone98.example.jvm.PermGenOomMock");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
