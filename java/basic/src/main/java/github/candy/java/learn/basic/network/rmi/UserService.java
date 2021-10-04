package github.candy.java.learn.basic.network.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserService extends Remote {
    String getUser() throws RemoteException;
}
