package pers.study.design.proxy.jdk;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import pers.study.design.proxy.common.UserDao;
import pers.study.design.proxy.common.UserDaoImpl;

public class JdkProxyTest {
//    @Test
//    public void testCreateJdkProxy() {
//        UserDaoImpl userDaoImpl = new UserDaoImpl();
//        //创建JDK代理
//        JdkProxy jdkProxy = new JdkProxy(userDaoImpl);
//        //创建代理对象
//        UserDao proxy = jdkProxy.createProxy();
//        //添加用户
//        proxy.addUser();
//    }

    @Test
    public void linkTest() {
        long start = System.currentTimeMillis();
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 10000000; i++) {
            list.add(i);
        }
        System.err.println(System.currentTimeMillis() - start);
    }

    @Test
    public void arrayList() {
        long start = System.currentTimeMillis();
        List<Integer> list = new ArrayList<>(10000000);
        for (int i = 0; i < 10000000; i++) {
            list.add(i);
        }
        System.err.println(System.currentTimeMillis() - start);
    }

    @Test
    public void arrayList2() {
        long start = System.currentTimeMillis();
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 10000000; i++) {
            list.add(i);
        }
        System.err.println(System.currentTimeMillis() - start);
    }
}
