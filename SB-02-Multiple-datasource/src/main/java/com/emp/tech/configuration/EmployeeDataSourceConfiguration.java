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
@EnableJpaRepositories(basePackages = "com.emp.tech.employee.repository",
        entityManagerFactoryRef = "employeeEntityManagerFactory",
        transactionManagerRef= "employeeTransactionManager")
public class EmployeeDataSourceConfiguration {

	@Autowired
    private Environment env;

    @Bean(name = "employeeDataSource")
    @ConfigurationProperties(prefix = "spring.employee.datasource")
    public DataSource employeeDataSource() {
    	return DataSourceBuilder.create().build();
    }

    @Bean(name = "employeeEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean employeeEntityManagerFactory(@Qualifier("employeeDataSource") DataSource employeeDataSource) {

    	LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(employeeDataSource);
 
        // Scan Entities in Package:
        em.setPackagesToScan(new String[] {"com.emp.tech.employee.model"});
        em.setPersistenceUnitName("****** employee_database ******");
 
        // JPA & Hibernate
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
 
        HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", "update");// this line add table if not exist # drop n create table, good for testing, comment this in production
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		
        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();
        return em;
    }

    @Bean
    public PlatformTransactionManager employeeTransactionManager(
			final @Qualifier("employeeEntityManagerFactory") LocalContainerEntityManagerFactoryBean employeeEntityManagerFactory) {
        return new JpaTransactionManager(employeeEntityManagerFactory.getObject());
    }

}
