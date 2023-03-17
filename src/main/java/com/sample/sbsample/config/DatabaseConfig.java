package com.sample.sbsample.config;

import java.util.Base64;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableJpaRepositories(basePackages = "com.sample.sbsample.dbbase.repo",entityManagerFactoryRef = "primaryEntityManagerFactory",transactionManagerRef = "primaryTransactionManager")
public class DatabaseConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSourceProperties primaryDataSourceProperties() {
        return new DataSourceProperties();
    }
    
    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.configuration")
    public DataSource primaryDataSource( @Qualifier("primaryDataSourceProperties") DataSourceProperties dataSourceProperties) {
        String password = new String(Base64.getDecoder().decode(dataSourceProperties.getPassword()));
        dataSourceProperties.setPassword(password);
        return dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
    
    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean primaryEntityManagerFactory(EntityManagerFactoryBuilder builder,@Qualifier("primaryDataSource") DataSource dataSource) {
        return builder
                    .dataSource(dataSource)
                    .packages("com.sample.sbsample.dbbase.entity")
                    .persistenceUnit("primaryEntityManager")
                    .build();
    }
    
    @Primary
    @Bean
    public PlatformTransactionManager primaryTransactionManager(@Qualifier("primaryEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
