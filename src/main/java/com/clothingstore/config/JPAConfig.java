package com.clothingstore.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration //khai báo đây là file config EntityManagerFactory
@EnableJpaRepositories(basePackages = {"com.clothingstore.repository"})
@EnableTransactionManagement // khai báo EntityTransaction cho file config để bật chức năng EntityTransaction
public class JPAConfig {
			// config EntityManagerFactory
		@Bean
		public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
			LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
			emf.setDataSource(datasourcePostgresHost());
			emf.setPersistenceUnitName("persistence-data");
			JpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
			emf.setJpaVendorAdapter(adapter);
			emf.setJpaProperties(Addproperties());
			return emf;
		}
		
		@Bean
		public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
			JpaTransactionManager manager = new JpaTransactionManager();
			manager.setEntityManagerFactory(entityManagerFactory);
			return manager;
		}
		
		@Bean
		public PersistenceExceptionTranslationPostProcessor exceptionTranlation() {
			
			return new PersistenceExceptionTranslationPostProcessor();
			
		}
		
//		@Bean
//		public DataSource datasourceMysql() {
//			DriverManagerDataSource datasource = new DriverManagerDataSource();
//			datasource.setDriverClassName("com.mysql.jdbc.Driver");
//			datasource.setUrl("jdbc:mysql://localhost:3306/clothingstore?createDatabaseIfNotExist=true");
//			datasource.setUsername("root");
//			datasource.setPassword("123456");
//			return datasource;
//		}
		
//		@Bean
//		public DataSource datasourcePostgres() {
//			DriverManagerDataSource datasource = new DriverManagerDataSource();
//			datasource.setDriverClassName("org.postgresql.Driver");
//			datasource.setUrl("jdbc:postgresql://127.0.0.1:5432/clothingstore");
//			datasource.setUsername("postgres");
//			datasource.setPassword("123456");
//			return datasource;
//		}
		
		@Bean
		public DataSource datasourcePostgresHost() {
			DriverManagerDataSource datasource = new DriverManagerDataSource();
			datasource.setDriverClassName("org.postgresql.Driver");
			datasource.setUrl("jdbc:postgresql://3.214.2.141:5432/d2gfkdt9ur4js8");
			datasource.setUsername("uufzgkraudizio");
			datasource.setPassword("d6576fbf00a1a9e24486c0adccdfec8761023624a547c80867ee31f8bd310ede");
			return datasource;
		}
		Properties Addproperties() {
			Properties properties = new Properties();
			properties.setProperty("hibernate.hbm2ddl.auto", "update");
			//properties.setProperty("hibernate.hbm2ddl.auto", "none");
			return properties;
		}
}
