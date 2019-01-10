package com.samlic.emulator.jdbc;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
public class DataSourceConfiguration {

	@Value("${spring.datasource.type}")  
	private Class<? extends DataSource> dataSourceType;
	
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
    	return DataSourceBuilder.create().type(dataSourceType).build();
    }
    
    @Bean(name = "transactionManager")  
    @Primary
    public PlatformTransactionManager transactionManager() throws Exception {
        return new DataSourceTransactionManager(dataSource());
    }
    
    @Bean
    public JdbcTemplate jdbcTemplate() throws Exception {
    	return new JdbcTemplate(dataSource());
    }
    
    @Bean
    public TransactionTemplate transactionTemplate() throws Exception {
    	return new TransactionTemplate(transactionManager());
    }
}
