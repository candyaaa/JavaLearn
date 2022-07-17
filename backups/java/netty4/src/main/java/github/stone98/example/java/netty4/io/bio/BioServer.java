package github.stone98.example.java.netty4.io.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import lombok.extern.slf4j.Slf4j;

/**
 * @author stone
 * @date 2021/10/15
 */
@Slf4j
public class BioServer {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            int port = 8080;
            serverSocket = new ServerSocket(port);
            log.info("服务端启动完成,监听【{}】端口", port);
            Socket socket = serverSocket.accept();
            long socketTimeMillis = System.currentTimeMillis();
            log.info("成功建立连接，等待客户端发送请求");
            // 因为是阻塞I/O，所以readLine方法会阻塞，直到内核准备好数据，才进行响应
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String requestContent = in.readLine();
            long acceptRequestTimeMillis = System.currentTimeMillis();
            log.info("成功建立请求之后，等待【{}】毫秒之后，成功接收客户端的请求，请求内容为:【{}】", acceptRequestTimeMillis - socketTimeMillis, requestContent);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
