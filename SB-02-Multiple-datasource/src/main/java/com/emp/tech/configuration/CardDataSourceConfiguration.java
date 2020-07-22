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
@EnableJpaRepositories(basePackages = "com.emp.tech.card.repository",
        entityManagerFactoryRef = "cardEntityManagerFactory",
        transactionManagerRef= "cardTransactionManager")
public class CardDataSourceConfiguration {

	@Autowired
    private Environment env;

    @Bean(name = "cardDataSource")
    @ConfigurationProperties(prefix = "spring.card.datasource")
    public DataSource cardDataSource() {
    	return DataSourceBuilder.create().build();
    }

    @Bean(name = "cardEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean cardEntityManagerFactory(@Qualifier("cardDataSource") DataSource cardDataSource) {

    	LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(cardDataSource);
 
        // Scan Entities in Package:
        em.setPackagesToScan(new String[] {"com.emp.tech.card.model"});
        em.setPersistenceUnitName("****** card_database ******");
 
        // JPA & Hibernate
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
 
        HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");// this line add table if not exist # drop n create table, good for testing, comment this in production
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		
        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();
        return em;
    }

    @Bean
    public PlatformTransactionManager cardTransactionManager(
			final @Qualifier("cardEntityManagerFactory") LocalContainerEntityManagerFactoryBean cardEntityManagerFactory) {
        return new JpaTransactionManager(cardEntityManagerFactory.getObject());
    }

}
