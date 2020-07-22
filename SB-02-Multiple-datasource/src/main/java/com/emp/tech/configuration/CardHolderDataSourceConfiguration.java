package com.emp.tech.configuration;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.emp.tech.cardholder.repository",
        entityManagerFactoryRef = "cardHolderEntityManagerFactory",
        transactionManagerRef= "cardHolderTransactionManager")
public class CardHolderDataSourceConfiguration {

	@Autowired
    private Environment env;

    @Bean(name = "cardholderDataSource")
    @ConfigurationProperties("spring.cardholder.datasource")
    public DataSource cardholderDataSource() {
    	return DataSourceBuilder.create().build();
    }

    @Bean(name = "cardHolderEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean cardHolderEntityManagerFactory(@Qualifier("cardholderDataSource") DataSource cardholderDataSource) {

    	LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(cardholderDataSource);
 
        // Scan Entities in Package:
        em.setPackagesToScan(new String[] {"com.emp.tech.cardholder.model"});
        em.setPersistenceUnitName("****** cardholder_database ******");
 
        // JPA & Hibernate
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
 
        HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		
        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();
        return em;
    }

    @Bean
    public PlatformTransactionManager cardHolderTransactionManager(
            final @Qualifier("cardHolderEntityManagerFactory") LocalContainerEntityManagerFactoryBean cardHolderEntityManagerFactory) {
        return new JpaTransactionManager(cardHolderEntityManagerFactory.getObject());
    }
}
