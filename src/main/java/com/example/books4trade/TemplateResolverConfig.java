package com.example.books4trade;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;


@Configuration
public class TemplateResolverConfig {

    @Bean
    public SpringResourceTemplateResolver userTemplateResolver(){
        SpringResourceTemplateResolver userTemplateResolver = new SpringResourceTemplateResolver();
        userTemplateResolver.setPrefix("classpath:/templates/users/");
        userTemplateResolver.setSuffix(".html");
        userTemplateResolver.setTemplateMode(TemplateMode.HTML);
        userTemplateResolver.setOrder(0);
        userTemplateResolver.setCheckExistence(true);
        return userTemplateResolver;
    }

    @Bean
    public SpringResourceTemplateResolver booksTemplateResolver(){
        SpringResourceTemplateResolver bookTemplateResolver = new SpringResourceTemplateResolver();
        bookTemplateResolver.setPrefix("classpath:/templates/books/");
        bookTemplateResolver.setSuffix(".html");
        bookTemplateResolver.setTemplateMode(TemplateMode.HTML);
        bookTemplateResolver.setOrder(1);
        bookTemplateResolver.setCheckExistence(true);
        return bookTemplateResolver;
    }
    @Bean
    public SpringResourceTemplateResolver reviewsTemplateResolver(){
        SpringResourceTemplateResolver reviewsTemplateResolver = new SpringResourceTemplateResolver();
        reviewsTemplateResolver.setPrefix("classpath:/templates/reviews/");
        reviewsTemplateResolver.setSuffix(".html");
        reviewsTemplateResolver.setTemplateMode(TemplateMode.HTML);
        reviewsTemplateResolver.setOrder(2);
        reviewsTemplateResolver.setCheckExistence(true);
        return reviewsTemplateResolver;
    }
    @Bean
    public SpringResourceTemplateResolver ownedBooksTemplateResolver(){
        SpringResourceTemplateResolver ownedBooksTemplateResolver = new SpringResourceTemplateResolver();
        ownedBooksTemplateResolver.setPrefix("classpath:/templates/owned-books/");
        ownedBooksTemplateResolver.setSuffix(".html");
        ownedBooksTemplateResolver.setTemplateMode(TemplateMode.HTML);
        ownedBooksTemplateResolver.setCharacterEncoding("UTF-8");
        ownedBooksTemplateResolver.setOrder(3);
        ownedBooksTemplateResolver.setCheckExistence(true);
        return ownedBooksTemplateResolver;
    }
    @Bean
    public SpringResourceTemplateResolver errorTemplateResolver(){
        SpringResourceTemplateResolver errorTemplateResolver = new SpringResourceTemplateResolver();
        errorTemplateResolver.setPrefix("classpath:/templates/error/");
        errorTemplateResolver.setSuffix(".html");
        errorTemplateResolver.setTemplateMode(TemplateMode.HTML);
        errorTemplateResolver.setOrder(4);
        errorTemplateResolver.setCheckExistence(true);
        return errorTemplateResolver;
    }
    @Bean
    public SpringResourceTemplateResolver tradesTemplateResolver(){
        SpringResourceTemplateResolver tradesTemplateResolver = new SpringResourceTemplateResolver();
        tradesTemplateResolver.setPrefix("classpath:/templates/trades/");
        tradesTemplateResolver.setSuffix(".html");
        tradesTemplateResolver.setTemplateMode(TemplateMode.HTML);
        tradesTemplateResolver.setOrder(5);
        tradesTemplateResolver.setCheckExistence(true);
        return tradesTemplateResolver;
    }
}
