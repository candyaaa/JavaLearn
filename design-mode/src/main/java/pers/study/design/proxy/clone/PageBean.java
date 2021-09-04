package pers.study.design.proxy.clone;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.Data;

/**
 * @author shikui@tidu.com
 * @date 2021/9/4
 */
@Data
public class PageBean<T> implements Cloneable {
    private List<T> data = Collections.emptyList();

    private Map<String, String> query;

    private Integer count;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
