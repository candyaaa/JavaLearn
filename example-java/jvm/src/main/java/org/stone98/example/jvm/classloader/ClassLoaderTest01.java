package org.stone98.example.jvm.classloader;

public class ClassLoaderTest01 {
    
    public static void main(String[] args) {
        // 获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        // 输出sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(systemClassLoader);
        
        // 通过系统类加载器获取它上层的扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        // 输出sun.misc.Launcher$ExtClassLoader@4554617c
        System.out.println(extClassLoader);
        
        // 获取扩展类加载器的上层启动类加载器，由输出可知启动类加载器获取不到，所以输出为null
        ClassLoader boostrapClassLoader = extClassLoader.getParent();
        // 输出null
        System.out.println(boostrapClassLoader);
        
        // 对于用户自定义类的类加载器，从输出结果得知使用系统默认的AppClassLoader进行加载
        ClassLoader classLoader = ClassLoaderTest01.class.getClassLoader();
        // 输出sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(classLoader);
    
        // 由输出为null得知，String类使BootstrapClassLoader进行加载
        ClassLoader stringClassLoader = String.class.getClassLoader();
        // 输出null
        System.out.println(stringClassLoader);
    }

}
