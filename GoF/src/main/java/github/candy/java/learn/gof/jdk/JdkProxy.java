package github.candy.java.learn.gof.jdk;


import github.candy.java.learn.gof.common.AuthCheck;
import github.candy.java.learn.gof.common.Report;
import github.candy.java.learn.gof.common.UserDao;
import github.candy.java.learn.gof.common.UserDaoImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author shiKui
 * @descrption jdk动态代理
 */
public class JdkProxy implements InvocationHandler {

    /**
     * 要被代理的目标对象
     */
    private UserDaoImpl target;

    public JdkProxy(UserDaoImpl target) {
        this.target = target;
    }

    /**
     * 创建代理类
     *
     * @return
     */
    public UserDao createProxy() {
        return (UserDao) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    /**
     * 调用被代理类(目标对象)的任意方法都会触发invoke方法
     *
     * @param proxy  代理类
     * @param method 被代理类的方法
     * @param args   被代理类的方法参数
     * @return
     * @throws Throwable
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        final String methodName = "addUser";
        // 过滤不需要该业务的方法
        if (methodName.equals(method.getName())) {
            // 调用前验证权限
            AuthCheck.authCheck();

            // 调用目标对象的方法
            Object result = method.invoke(target, args);

            // 记录日志数据
            Report.recordLog();

            return result;
        }
        // 如果不需要增强直接执行原方法
        return method.invoke(target, args);
    }

}
