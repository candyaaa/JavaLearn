package github.candy.seek.knowledge.java.netty4.前置知识.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

/**
 * @author candy
 * @date 2021/10/15
 */
@Slf4j
public class TimeServer {
    private static int i = 1;
    public static void main(String[] args) {
        int port = 8080;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            log.info("The time server is start in port:{}", port);
            Socket socket;
            while (true) {
                socket = serverSocket.accept();
                log.info("accept complete...wait operation...");
                new Thread(new TimeServerHandler(socket),"timeServerHandler-" + i++).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                log.info("The time server close");
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class TimeServerHandler implements Runnable {

        private Socket socket;

        public TimeServerHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            BufferedReader in = null;
            PrintWriter out;
            try {
                in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
                out = new PrintWriter(this.socket.getOutputStream(), true);
                String currentTime;
                String body;
                while (true) {
                    body = in.readLine();
                    if (body == null) {
                        break;
                    }
                    log.info("The time server receive request:{}", body);
                    currentTime = "QUERY TIME REQUEST".equalsIgnoreCase(body)
                            ? new Date(System.currentTimeMillis()).toString()
                            : "BAD REQUEST";
                    out.println(currentTime);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (socket != null) {
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
