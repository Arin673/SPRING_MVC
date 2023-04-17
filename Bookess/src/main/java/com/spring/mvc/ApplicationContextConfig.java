package com.spring.mvc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class ApplicationContextConfig {
    @Value("${mysqldriver}")
    private String driver;
    @Value("${mysqlurl}")
    private String url;
    @Value("${mysqluser}")
    private String uname;
    @Value("${mysqlpwd}")
    private String pwd;

    @Bean
    public DataSource dataSource(){
        System.out.println("Creating Data Source");
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(uname);
        ds.setPassword(pwd);
        return ds;
    }
    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver(){
        System.out.println("view resolver");
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");

        return viewResolver;
    }
    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.spring.mvc.entity");
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        System.out.println("Session Factory Created");

        return sessionFactoryBean;
    }

    private final Properties hibernateProperties(){
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty("hibernate.dialect","org.hibernate.dialect.MySQLDialect");
        hibernateProperties.setProperty("hibernate.show_sql","true");
        return hibernateProperties;
    }
    @Bean
    public PlatformTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}
