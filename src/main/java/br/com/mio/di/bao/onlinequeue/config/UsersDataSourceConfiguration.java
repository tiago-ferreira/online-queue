package br.com.mio.di.bao.onlinequeue.config;

import org.apache.commons.dbcp2.BasicDataSource;
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

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.mio.di.bao.onlinequeue.repository.users",
        entityManagerFactoryRef = "usersEntityManagerFactory",
        transactionManagerRef= "usersTransactionManager")
public class UsersDataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.users")
    public DataSourceProperties usersDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.users.configuration")
    public DataSource usersDataSource() {
        return usersDataSourceProperties().initializeDataSourceBuilder()
                .type(BasicDataSource.class).build();
    }

    @Primary
    @Bean(name = "usersEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean usersEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(usersDataSource())
                .packages("br.com.mio.di.bao.onlinequeue.model.users")
                .build();
    }

    @Bean
    @Primary
    public PlatformTransactionManager usersTransactionManager(
            final @Qualifier("usersEntityManagerFactory") LocalContainerEntityManagerFactoryBean usersEntityManagerFactory) {
        return new JpaTransactionManager(usersEntityManagerFactory.getObject());
    }
}
