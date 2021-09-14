package github.candy.java.learn.gof.cglib;


import github.candy.java.learn.gof.common.AuthCheck;
import github.candy.java.learn.gof.common.Report;
import github.candy.java.learn.gof.common.UserDaoImpl;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @description cglib代理
 * @author shiKui
 */
public class CglibProxy implements MethodInterceptor {

    /**
     * 被代理的目标类
     */
    private UserDaoImpl target;

    public CglibProxy(UserDaoImpl target) {
        super();
        this.target = target;
    }

    /**
     * 创建代理类
     * @return
     */
    public UserDaoImpl createProxy(){
        // 使用CGLIB生成代理:
        // 1.声明增强类实例,用于生产代理类
        Enhancer enhancer = new Enhancer();
        // 2.设置被代理类字节码，CGLIB根据字节码生成被代理类的子类
        enhancer.setSuperclass(target.getClass());
        // 3.//设置回调函数，即一个方法拦截
        enhancer.setCallback(this);
        // 4.创建代理:
        return (UserDaoImpl) enhancer.create();
    }

    /**
     * 回调函数
     * @param proxy 代理对象
     * @param method 委托类方法
     * @param args 方法参数
     * @param methodProxy 每个被代理的方法都对应一个MethodProxy对象，
     *                    methodProxy.invokeSuper方法最终调用委托类(目标类)的addAdmin方法
     * @return
     * @throws Throwable
     */
    public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        final String methodName = "addUser";
        if(methodName.equals(method.getName())){
            // 校验权限
            AuthCheck.authCheck();
            // 调用目标类的方法
            Object obj = methodProxy.invokeSuper(proxy, args);
            // 记录日志
            Report.recordLog();
            return obj;
        }
        return methodProxy.invokeSuper(proxy, args);
    }
}
