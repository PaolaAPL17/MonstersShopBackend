package com.femcoders.monsters_shop.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;

@Configuration
public class DBConfig {
    @Autowired
    private Environment env;
    @Bean
    public DataSource dataSource() {
        Dotenv dotenv = Dotenv.configure().directory("./").ignoreIfMalformed().ignoreIfMissing().load();
        String dbUrl = dotenv.get("DB_URL");
        String dbUser = dotenv.get("DB_USER");
        String dbPassword = dotenv.get("DB_PASSWORD");
        String dbDriver = dotenv.get("DB_DRIVER");
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(dbDriver);
        dataSourceBuilder.url(dbUrl);
        dataSourceBuilder.username(dbUser);
        dataSourceBuilder.password(dbPassword);
        return dataSourceBuilder.build();
    }
}