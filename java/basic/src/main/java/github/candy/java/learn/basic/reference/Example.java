package github.candy.java.learn.basic.reference;

import java.util.HashMap;

public class Example {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("example被jvm回收");
    }

    public static void main(String[] args) {
        // 01 11
        System.out.println(32 & 16);
        HashMap map = new HashMap();
        map.put("1","1");
        map.get("");
        String tempStr = "";
        String str2 = Integer.toBinaryString(101111);
        //判断一下：如果转化为二进制为0或者1或者不满8位，要在数后补0
        int bit = 8-str2.length();
        if(str2.length()<8){
            for(int j=0; j<bit; j++){
                str2 = "0"+str2;
            }
        }
        tempStr += str2;
        System.out.println(tempStr);
    }
}
