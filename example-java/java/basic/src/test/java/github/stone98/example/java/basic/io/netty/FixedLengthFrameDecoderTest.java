package github.stone98.example.java.basic.io.netty;

/**
 * @author shikui@tidu.com
 * @date 2021/7/10
 */
public class FixedLengthFrameDecoderTest {
    public static void main(String[] args) {
        System.out.println(DbPrivilegeType.OTHER);
    }
}

enum DbPrivilegeType {
    /**
     * 只读
     */
    READONLY,
    /**
     * 读写
     */
    READWRITE,
    /**
     * 其他
     */
    OTHER
}
