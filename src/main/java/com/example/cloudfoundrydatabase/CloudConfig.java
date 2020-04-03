package com.example.cloudfoundrydatabase;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnCloudPlatform;
import org.springframework.boot.cloud.CloudPlatform;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ConditionalOnCloudPlatform(CloudPlatform.CLOUD_FOUNDRY)
public class CloudConfig extends AbstractCloudConfig {

    private static final int MAX_POOL_SIZE = 4;
    private static final long MAX_LIFETIME = 900000L;
    private static final String CONNECTION_TEST_QUERY = "select case when @@read_only + @@innodb_read_only = 0 then 1 else (select table_name from information_schema.tables) end as `1`";

    @Value("${spring.datasource.url}")
    String jdbcUrl;

    @Value("${spring.datasource.username}")
    String username;

    @Value("${spring.datasource.password}")
    String password;

    @Bean
    public DataSource dataSource() {
        // Create DataSource
        HikariDataSource ds = new HikariDataSource();

        // Set important connection parameters
        ds.setJdbcUrl(jdbcUrl);
        ds.setUsername(username);
        ds.setPassword(password);

        // Set the connection test query to prevent connections to a reader node
        ds.setConnectionTestQuery(CONNECTION_TEST_QUERY);

        // Configure recommended values
        ds.setMaxLifetime(MAX_LIFETIME);
        ds.setMaximumPoolSize(MAX_POOL_SIZE);
        return ds;
    }

}
