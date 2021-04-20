package org.example.spring.transactional.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GoodDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void loseRepertory(int repertory) {
        String sql = "UPDATE tab_good SET repertory = repertory - ?";
        jdbcTemplate.update(sql, repertory);
    }
}
