package dev.sebsven;

import dev.sebsven.service.PostService;
import dev.sebsven.service.SubscribersService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.simple.JdbcClient;

import javax.sql.DataSource;

@SpringBootApplication
public class MultipleDsWebapi {


    public static void main(String[] args) {
        SpringApplication.run(MultipleDsWebapi.class, args);
    }

    @Bean
    public JdbcClient blogJdbcClient(@Qualifier("blogDataSource") DataSource dataSource) {
        return JdbcClient.create(dataSource);
    }

    @Bean
    public JdbcClient subscriberJdbcClient(@Qualifier("subscribersDataSource") DataSource dataSource) {
        return JdbcClient.create(dataSource);
    }
    
    @Bean
    public CommandLineRunner commandLineRunner(PostService postService, SubscribersService subscribersService) {
        return args -> {
            postService.findAll()
                    .forEach(System.out::println);
            subscribersService.getSubscribers()
                    .forEach(System.out::println);
        };
    }

    @Bean
    public CommandLineRunner dsCommandLineRunner(@Qualifier("blogDataSource") DataSource blogDataSource,
                                                 @Qualifier("subscribersDataSource") DataSource subscribersDataSource) {
        return args -> {
            System.out.println(blogDataSource.getConnection().getMetaData().getURL());
            System.out.println(subscribersDataSource.getConnection().getMetaData().getURL());
        };
    }
}
