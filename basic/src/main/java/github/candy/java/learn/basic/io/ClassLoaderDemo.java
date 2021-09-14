package github.candy.java.learn.basic.io;

import java.util.Optional;

/**
 * @author shikui@tidu.com
 * @date 2021/7/26
 */
public class ClassLoaderDemo {
    public static void main(String[] args) {
        User user = new User();
        user.setUser(null);
    }

    static class User{
        private String user;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
    }
}
