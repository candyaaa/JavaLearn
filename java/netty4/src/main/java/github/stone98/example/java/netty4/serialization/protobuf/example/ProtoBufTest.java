package github.stone98.example.java.netty4.serialization.protobuf.example;

import com.google.protobuf.InvalidProtocolBufferException;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * ref: <a>https://blog.csdn.net/yuanlaijike/article/details/103659430</a>
 *
 * @author candy
 * @date 2021/10/19
 */
@Slf4j
public class ProtoBufTest {
    @Test
    public void parseTest() {
        serialization.protobuf.example.UserProto.User.Builder builder = serialization.protobuf.example.UserProto.User.newBuilder();
        builder.setName("Zhang San");
        builder.setAge(18);
        builder.setSex(serialization.protobuf.example.SexEnumProto.SexEnum.MALE);

        serialization.protobuf.example.UserProto.User user = builder.build();
        log.info("Parse before: {}", user);

        byte[] byteArray = user.toByteArray();
        log.info("User Byte: {}", Arrays.toString(byteArray));

        try {
            serialization.protobuf.example.UserProto.User parseAfterUser = serialization.protobuf.example.UserProto.User.parseFrom(byteArray);
            log.info("Parse after: {}", parseAfterUser.toString());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}
