package pers.study.design.proxy.jdk;

import org.junit.Test;
import pers.study.design.proxy.common.UserDao;
import pers.study.design.proxy.common.UserDaoImpl;

public class JdkProxyTest {
    @Test
    public void testCreateJdkProxy(){
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        //创建JDK代理
        JdkProxy jdkProxy = new JdkProxy(userDaoImpl);
        //创建代理对象
        UserDao proxy = jdkProxy.createProxy();
        //添加用户
        proxy.addUser();
    }
}
