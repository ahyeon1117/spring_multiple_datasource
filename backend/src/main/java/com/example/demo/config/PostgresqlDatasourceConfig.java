package com.example.demo.config;

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
@EnableJpaRepositories(
    basePackages = "com.example.repository.postgresql",
	entityManagerFactoryRef = "postgresqlEntityManagerFactory",
	transactionManagerRef = "postgresqlTransactionManager"
)
public class PostgresqlDatasourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties("datasource.postgresql")
    public DataSourceProperties postgresqlDataSourceProperties(){
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource postgresqlDataSource(){
        return postgresqlDataSourceProperties()
            .initializeDataSourceBuilder()
            .type(HikariDataSource.class)
            .build();
    }

    @Bean(name = "postgresqlEntityManager")
    @Primary
    public LocalContainerEntityManagerFactoryBean postgresqlEntityManager(
            EntityManagerFactoryBuilder builder) {
        return builder.dataSource(postgresqlDataSource())
                .build();
    }

    @Bean(name = "postgresqlTransactionManager")
    @Primary
    public PlatformTransactionManager postgresqlTransactionManager(
            @Qualifier("postgresqlEntityManager") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {
        return new JpaTransactionManager(entityManagerFactoryBean.getObject());
    }
}
