package org.stone98.example.jvm.classloader;

import sun.misc.Launcher;
import sun.security.ec.CurveDB;

import java.net.URL;
import java.security.Provider;

public class ClassLoaderTest02 {
    
    public static void main(String[] args) {
        System.out.println("打印BootstrapClassLoader加载的路径：");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println(urL.toExternalForm());
        }
        // 从上述路径中随意找一个Class获取它的类加载器，看是否符合我们的猜想。
        ClassLoader bootstrapClassLoader = Provider.class.getClassLoader();
        // 从输出结果得知，Provider的类加载器为BootstrapClassLoader
        System.out.println(bootstrapClassLoader);
        System.out.println("打印ExtClassLoader加载的路径：");
        String extDirs = System.getProperty("java.ext.dirs");
        for (String path : extDirs.split(";")) {
            System.out.println(path);
        }
        // 从上述路径中随意找一个Class获取它的类加载器，看是否符合我们的猜想。
        ClassLoader extClassLoader = CurveDB.class.getClassLoader();
        // 从输出结果得知，符合我们的验证
        System.out.println(extClassLoader);
    }
    
}
