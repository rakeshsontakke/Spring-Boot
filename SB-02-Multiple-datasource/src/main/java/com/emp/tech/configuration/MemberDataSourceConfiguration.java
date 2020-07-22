package com.emp.tech.configuration;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
//@PropertySources({@PropertySource("classpath:application.properties")})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.emp.tech.member.repository",
        entityManagerFactoryRef = "memberEntityManagerFactory",
        transactionManagerRef= "memberTransactionManager"
)
public class MemberDataSourceConfiguration {
	
	@Autowired
    private Environment env;

	@Primary
    @Bean(name = "memberDataSource")
    @ConfigurationProperties(prefix = "spring.member.datasource")
    public DataSource memberDataSource() {

    	return DataSourceBuilder.create().build();
    	// OR
		//    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
		//    	dataSource.setDriverClassName(env.getProperty("spring.member.datasource.driver-class-name"));
		//	    dataSource.setUrl(env.getProperty("spring.member.datasource.jdbcUrl"));
		//	    dataSource.setUsername(env.getProperty("spring.member.datasource.username"));
		//	    dataSource.setPassword(env.getProperty("spring.member.datasource.password"));
		//	    return dataSource;
    }

    @Primary
    @Bean(name = "memberEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean memberEntityManagerFactory(@Qualifier("memberDataSource") DataSource memberDataSource) {
    	
    	LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(memberDataSource);
 
        // Scan Entities in Package:
        em.setPackagesToScan(new String[] {"com.emp.tech.member.model"});
        em.setPersistenceUnitName("****** member_database ******");
 
        // JPA & Hibernate
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
 
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		
        em.setJpaPropertyMap(properties);
        em.afterPropertiesSet();
        return em;

    	
//        HashMap<String, Object> properties = new HashMap<>();
//		properties.put("hibernate.hbm2ddl.auto", "update");
//		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
//        EntityManagerFactoryBuilder builder = null;
//		return builder
//				.dataSource(memberDataSource)
//				.properties(properties)
//				.packages("com.emp.tech.model.member")
//				.persistenceUnit("member_database")
//				.build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager memberTransactionManager(
            final @Qualifier("memberEntityManagerFactory") LocalContainerEntityManagerFactoryBean memberEntityManagerFactory) {
        return new JpaTransactionManager(memberEntityManagerFactory.getObject());
    }

}
