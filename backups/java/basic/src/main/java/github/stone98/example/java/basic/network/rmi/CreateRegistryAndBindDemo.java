package github.stone98.example.java.basic.network.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class CreateRegistryAndBindDemo {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        UserService userService = new UserServiceImpl();
        // 创建注册中心
        LocateRegistry.createRegistry(9999);
        // 绑定
        Naming.rebind("rmi://127.0.0.1:9999/getUser",userService);
    }
}
