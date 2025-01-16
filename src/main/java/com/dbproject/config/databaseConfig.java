package com.dbproject.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class databaseConfig {
    
    @Bean
    public JdbcTemplate getJdbcTemplate(final DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
