package pers.study.design.proxy.clone;

import com.alibaba.fastjson.JSON;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author shikui@tidu.com
 * @date 2021/9/4
 */
public class CloneMain {
    public static void main(String[] args) throws CloneNotSupportedException {
        HashMap<String, String> stringStringHashMap = new HashMap<String, String>();
        stringStringHashMap.put("test", "test");
        PageBean<User> pageBean = new PageBean<>();
        pageBean.setCount(1);
        pageBean.setData(Collections.singletonList(new User("shikui")));
        pageBean.setQuery(stringStringHashMap);

        PageBean<UserVo> clone = (PageBean<UserVo>) pageBean.clone();
        System.out.println(JSON.toJSONString(clone));
    }
}
