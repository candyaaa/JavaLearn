package pers.study.network.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserServiceImpl extends UnicastRemoteObject implements UserService {
    public UserServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String getUser() throws RemoteException {
        return "i am user info";
    }
}
