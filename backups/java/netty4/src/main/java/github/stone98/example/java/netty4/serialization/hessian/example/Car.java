package github.stone98.example.java.netty4.serialization.hessian.example;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import lombok.Data;
import lombok.ToString;

/**
 * @author shikui@tidu.com
 * @date 2021/11/13
 */
@ToString
@Data
public class Car implements Externalizable {

    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 3943382426658456693L;

    /**
     * 名称.
     */
    private String name;

    /**
     * 价格.
     */
    private Integer price;

    /**
     * 颜色.
     */
    private String color;

    /**
     * 长度.
     */
    private transient int length;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(name);
        out.writeInt(price);
        out.writeUTF(color);
        out.writeInt(length);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        System.out.println("[模拟日志记录]readExternal.");

        name = in.readUTF();
        price = in.readInt();
        color = in.readUTF();
        length = in.readInt();
    }

}
