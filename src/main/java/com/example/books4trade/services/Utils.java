package com.example.books4trade.services;

import com.example.books4trade.models.Book;
import com.example.books4trade.models.BookReview;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static String[] words = {"bookshelf","life","books","library","hardcover","pages","shakespeare","hemingway","twain","seuss","hugo"};
    private static String[] letters = {"a","b","c","d","f","g","l","k","x","y","o"};

    public static String buildRandomString(){
        String string;
        String word = words[randomInt(words.length)];
        String letter1 = letters[randomInt(letters.length)];
        String letter2 = letters[randomInt(letters.length)];
        int num1 = randomInt(9);
        int num2 = randomInt(80);
        int num3 = randomInt(500);
        int roll = randomInt(4);
        switch(roll){
            case 1:
                string = letter2 + num1 + word + num3 + letter1;
                break;
            case 2:
                string = word + num2 + num3 + letter1 + letter2;
                break;
            case 3:
                string = letter1 + num1 + num2 + word;
                break;
            case 4:
                string = letter2 + word + num1 + num2 + letter1;
                break;
            default:
                string = word + letter1 +letter2 + num1;
                break;
        }
        return string;
    }

    public static boolean randomDecision(){
        boolean decision = (randomInt(100) > 50);
        return decision;
    }

    public static int randomInt(int max){
        int randomNum = (int) (Math.random() * max);
        return randomNum;
    }

    public static List<Long> reviewRatings(List<BookReview> bookreviews){
        List<Long> allratings = new ArrayList<>();
        for(BookReview review : bookreviews ){
            allratings.add(review.getRating());
        }
        return allratings;
    }

    public static long reviewSum(List<Long> ratings){
        long sum = 0;
        for(long rating : ratings){
            sum += rating;
        }
        return sum;
    }

    public static List<Book> removeDuplicates(List<Book> readList){
        List<Book> books = new ArrayList<>();
        for(Book book : readList){
            if(!books.contains(book)){
                books.add(book);
            }
        }
        return books;
    }

}
