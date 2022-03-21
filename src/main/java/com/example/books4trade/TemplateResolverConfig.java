package com.example.books4trade;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@Configuration
public class TemplateResolverConfig {

    @Bean
    public ClassLoaderTemplateResolver userTemplateResolver(){
        ClassLoaderTemplateResolver userTemplateResolver = new ClassLoaderTemplateResolver();
        userTemplateResolver.setPrefix("users/");
        userTemplateResolver.setSuffix(".html");
        userTemplateResolver.setTemplateMode(TemplateMode.HTML);
        userTemplateResolver.setOrder(1);
        userTemplateResolver.setCheckExistence(true);
        return userTemplateResolver;
    }

    @Bean
    public ClassLoaderTemplateResolver booksTemplateResolver(){
        ClassLoaderTemplateResolver bookTemplateResolver = new ClassLoaderTemplateResolver();
        bookTemplateResolver.setPrefix("books/");
        bookTemplateResolver.setSuffix(".html");
        bookTemplateResolver.setTemplateMode(TemplateMode.HTML);
        bookTemplateResolver.setOrder(2);
        bookTemplateResolver.setCheckExistence(true);
        return bookTemplateResolver;
    }
    @Bean
    public ClassLoaderTemplateResolver reviewsTemplateResolver(){
        ClassLoaderTemplateResolver reviewsTemplateResolver = new ClassLoaderTemplateResolver();
        reviewsTemplateResolver.setPrefix("reviews/");
        reviewsTemplateResolver.setSuffix(".html");
        reviewsTemplateResolver.setTemplateMode(TemplateMode.HTML);
        reviewsTemplateResolver.setOrder(3);
        reviewsTemplateResolver.setCheckExistence(true);
        return reviewsTemplateResolver;
    }
    @Bean
    public ClassLoaderTemplateResolver ownedBooksTemplateResolver(){
        ClassLoaderTemplateResolver ownedBooksTemplateResolver = new ClassLoaderTemplateResolver();
        ownedBooksTemplateResolver.setPrefix("owned-books/");
        ownedBooksTemplateResolver.setSuffix(".html");
        ownedBooksTemplateResolver.setTemplateMode(TemplateMode.HTML);
        ownedBooksTemplateResolver.setCharacterEncoding("UTF-8");
        ownedBooksTemplateResolver.setOrder(4);
        ownedBooksTemplateResolver.setCheckExistence(true);
        return ownedBooksTemplateResolver;
    }
    @Bean
    public ClassLoaderTemplateResolver errorTemplateResolver(){
        ClassLoaderTemplateResolver errorTemplateResolver = new ClassLoaderTemplateResolver();
        errorTemplateResolver.setPrefix("error/");
        errorTemplateResolver.setSuffix(".html");
        errorTemplateResolver.setTemplateMode(TemplateMode.HTML);
        errorTemplateResolver.setOrder(5);
        errorTemplateResolver.setCheckExistence(true);
        return errorTemplateResolver;
    }
    @Bean
    public ClassLoaderTemplateResolver tradesTemplateResolver(){
        ClassLoaderTemplateResolver tradesTemplateResolver = new ClassLoaderTemplateResolver();
        tradesTemplateResolver.setPrefix("trades/");
        tradesTemplateResolver.setSuffix(".html");
        tradesTemplateResolver.setTemplateMode(TemplateMode.HTML);
        tradesTemplateResolver.setOrder(6);
        tradesTemplateResolver.setCheckExistence(true);
        return tradesTemplateResolver;
    }
}
