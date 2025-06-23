package com.carla.springboot.crud.springboot_crud.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.*;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.carla.springboot.crud.springboot_crud.repository.logistica",
    entityManagerFactoryRef = "logisticaEntityManager",
    transactionManagerRef = "logisticaTransactionManager"
)
public class LogisticaConfig {

    @Bean(name = "logisticaDataSource")
    @ConfigurationProperties(prefix = "logistica.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "logisticaEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("logisticaDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.carla.springboot.crud.springboot_crud.entities");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setPersistenceUnitName("logistica");
        return em;
    }

    @Bean(name = "logisticaTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("logisticaEntityManager") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}