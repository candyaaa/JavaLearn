package github.stone98.example.java.basic.network.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface UserService extends Remote {
    String getUser() throws RemoteException;
}
