package dev.sebsven.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.init.DataSourceScriptDatabaseInitializer;
import org.springframework.boot.sql.init.DatabaseInitializationMode;
import org.springframework.boot.sql.init.DatabaseInitializationSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.List;


@Configuration(proxyBeanMethods = false)
public class DataSourceConfiguration {

    @Bean
    @Primary
    @ConfigurationProperties("app.datasource.blog")
    public DataSourceProperties blogDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public DataSource blogDataSource(DataSourceProperties dataSourceProperties) {
        return dataSourceProperties.initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public DataSourceScriptDatabaseInitializer blogDataSourceScriptDatabaseInitializer(@Qualifier("blogDataSource") DataSource dataSource) {
        var settings = new DatabaseInitializationSettings();
        settings.setSchemaLocations(List.of("classpath:blog-schema.sql"));
        settings.setMode(DatabaseInitializationMode.EMBEDDED);
        return new DataSourceScriptDatabaseInitializer(dataSource, settings);
    }

    // SUBSCRIBERS ========================================================================================


    @Bean
    @ConfigurationProperties("app.datasource.subscribers")
    public DataSourceProperties subscribersDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    public DataSource subscribersDataSource(@Qualifier("subscribersDataSourceProperties") DataSourceProperties subscribersDataSourceProperties) {
        return DataSourceBuilder
                .create()
                .url(subscribersDataSourceProperties.getUrl())
                .username(subscribersDataSourceProperties.getUsername())
                .password(subscribersDataSourceProperties.getPassword())
                .build();
    }

    @Bean
    public DataSourceScriptDatabaseInitializer subscribersDataSourceScriptDatabaseInitializer(@Qualifier("subscribersDataSource") DataSource dataSource) {
        var settings = new DatabaseInitializationSettings();
        settings.setSchemaLocations(List.of("classpath:subscribers-schema.sql"));
        settings.setMode(DatabaseInitializationMode.EMBEDDED);
        return new DataSourceScriptDatabaseInitializer(dataSource, settings);
    }
}
