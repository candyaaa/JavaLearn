package pers.study.design.proxy.cglib;

import org.junit.Test;
import pers.study.design.proxy.common.UserDaoImpl;

/**
 * @author shiKui
 * @description cglib动态代理测试
 */
public class CglibProxyTest {

    @Test
    public void testCreateProxy() throws Exception {
        UserDaoImpl userDaoImpl = new UserDaoImpl();

        CglibProxy cgLibProxy = new CglibProxy(userDaoImpl);

        UserDaoImpl proxy = cgLibProxy.createProxy();

        proxy.addUser();
    }
}