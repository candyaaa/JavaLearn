package github.candy.java.seek.knowledge.spring.transactional.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class OrderDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void insertOrder(int goodId, int userId) {
        String sql = "INSERT INTO `tab_order`(`good_id`, `user_id`) VALUES (?,?)";
        jdbcTemplate.update(sql, goodId, userId);
    }
}
