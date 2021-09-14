package github.candy.java.learn.spring.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author candy
 * @date 2021/9/5 17:13
 */
@Data
@ToString
@Component
@PropertySource("classpath:dbConfig.properties")
public class DatabaseConfig {

    @Value("${db.username}")
    private String username;

    @Value("${db.password}")
    private String password;

    @Value("${db.driverClass}")
    private String driverClass;

    @Value("${db.jdbcUrl}")
    private String jdbcUr;
}
