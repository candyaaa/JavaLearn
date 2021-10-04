package github.candy.java.learn.gof.common;

import lombok.extern.slf4j.Slf4j;

/**
 * @author shiKui
 * @descrption 用户Dao实现层
 */
@Slf4j
public class UserDaoImpl implements UserDao {
    public void addUser() {
      log.info("添加用户...");
    }
}
