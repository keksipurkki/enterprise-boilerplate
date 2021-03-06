package com.enterprise.boilerplate.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * For configuring the persistence layer
 *
 */


@Configuration
@EnableTransactionManagement // @Transactional
// @EntityScan("com.enterprise.boilerplate.model") // if auto-configuration was used
class PersistenceConfig {

    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String HIBERNATE_DIALECT = "org.hibernate.dialect.MySQL5InnoDBDialect";

    private Environment env;

    @Autowired
    public PersistenceConfig(Environment env) {
        this.env = env;
        assert this.env != null;

    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.enterprise.boilerplate.model");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(JDBC_DRIVER);

        try {

            dataSource.setUrl(env.getProperty("com.enterprise.db.url"));
            dataSource.setUsername(env.getProperty("com.enterprise.db.username"));
            dataSource.setPassword(env.getProperty("com.enterprise.db.password"));
            dataSource.getConnection();

        } catch (SQLException e) {

            throw new RuntimeException("Cannot connect to the database you specified. Check your application properties.");


        } catch (java.lang.IllegalArgumentException e) {

            throw new RuntimeException("Cannot read database connection parameters from application properties");


        }

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.namingStrategy", "jpa");
        properties.setProperty("hibernate.show_sql", "false");
        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        properties.setProperty("hibernate.dialect", HIBERNATE_DIALECT);
        return properties;
    }
}