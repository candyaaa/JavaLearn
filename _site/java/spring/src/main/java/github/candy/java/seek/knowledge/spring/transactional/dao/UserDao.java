package github.candy.java.seek.knowledge.spring.transactional.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void loseBalance(double price) {
        String sql = "UPDATE `tab_user` SET balance = balance - ?";
        jdbcTemplate.update(sql, price);
    }
}
