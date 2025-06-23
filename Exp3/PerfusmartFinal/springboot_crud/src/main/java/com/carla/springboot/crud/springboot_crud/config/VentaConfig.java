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
    basePackages = "com.carla.springboot.crud.springboot_crud.repository.venta",
    entityManagerFactoryRef = "ventaEntityManager",
    transactionManagerRef = "ventaTransactionManager"
)
public class VentaConfig {
    @Bean(name = "ventaDataSource")
    @ConfigurationProperties(prefix = "venta.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "ventaEntityManager")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("ventaDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.carla.springboot.crud.springboot_crud.entities");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setPersistenceUnitName("venta");
        return em;
    }

    @Bean(name = "ventaTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("ventaEntityManager") EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }

}