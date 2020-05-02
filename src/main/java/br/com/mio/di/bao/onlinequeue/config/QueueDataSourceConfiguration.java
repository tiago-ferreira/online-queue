package br.com.mio.di.bao.onlinequeue.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.mio.di.bao.onlinequeue.repository.queue",
        entityManagerFactoryRef = "queueEntityManagerFactory",
        transactionManagerRef= "queueTransactionManager")
public class QueueDataSourceConfiguration {

    @Bean
    @ConfigurationProperties("app.datasource.queue")
    public DataSourceProperties queueDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @ConfigurationProperties("app.datasource.queue.configuration")
    public DataSource queueDataSource() {
        return queueDataSourceProperties().initializeDataSourceBuilder()
                .type(BasicDataSource.class).build();
    }

    @Bean(name = "queueEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean queueEntityManagerFactory(
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(queueDataSource())
                .packages("br.com.mio.di.bao.onlinequeue.model.queue","br.com.mio.di.bao.onlinequeue.model.users")
                .build();
    }

    @Bean
    public PlatformTransactionManager queueTransactionManager(
            final @Qualifier("queueEntityManagerFactory") LocalContainerEntityManagerFactoryBean queueEntityManagerFactory) {
        return new JpaTransactionManager(queueEntityManagerFactory.getObject());
    }
}
