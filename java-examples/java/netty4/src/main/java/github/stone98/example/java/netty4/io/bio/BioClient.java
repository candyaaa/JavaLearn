package github.stone98.example.java.netty4.io.bio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import lombok.extern.slf4j.Slf4j;

/**
 * @author stone
 * @date 2021/10/15
 */
@Slf4j
public class BioClient {
    public static void main(String[] args) {
        Socket socket = null;
        PrintWriter out = null;
        int port = 8080;
        try {
            socket = new Socket("127.0.0.1", port);
            // 建立连接之后，睡眠3000ms，再发送请求
            Thread.sleep(3000);
            out = new PrintWriter(socket.getOutputStream(), true);
            out.println("QUERY REQUEST");
            log.info("成功发送请求到服务端");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
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
