package github.candy.java.learn.basic.jvm;

import java.net.URL;

public class ClassLoaderDemo {
    public static void main(String[] args) {
        applicationClassLoader();
        extensionClassLoader();
        bootstrapClassLoader();
    }

    private static void applicationClassLoader(){
        String property = System.getProperty("java.class.path");
        System.out.println("application classloader loader context:");
        for (String s : property.split(";")) {
            System.out.println(s);
        }
        System.out.println(ClassLoader.getSystemClassLoader());
    }

    private static void extensionClassLoader(){
        String property = System.getProperty("java.ext.dirs");
        System.out.println("extension classloader loader context:");
        for (String s : property.split(";")) {
            System.out.println(s);
        }
        System.out.println(ClassLoader.getSystemClassLoader().getParent());
    }

    private static void bootstrapClassLoader(){
        URL[] urLs = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        System.out.println("bootstrap classloader loader context：");
        for (URL url : urLs) {
            System.out.println(url.toExternalForm());
        }
    }
}
