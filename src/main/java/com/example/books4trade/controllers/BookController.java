package com.example.books4trade.controllers;


import com.example.books4trade.models.Author;
import com.example.books4trade.models.Book;
import com.example.books4trade.models.OwnedBook;
import com.example.books4trade.models.User;
import com.example.books4trade.repositories.AuthorRepository;
import com.example.books4trade.repositories.BookRepository;
import com.example.books4trade.repositories.OwnedBookRepository;
import com.example.books4trade.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class BookController {
    @Value("${GB_API}")
    private String GBKey;

    private BookRepository booksDao;
    private UserRepository usersDao;
    private AuthorRepository authorsDao;
    private OwnedBookRepository ownedBooksDao;



    public BookController(BookRepository bookDao, UserRepository usersDao, AuthorRepository authorsDao, OwnedBookRepository ownedBooksDao){
        this.booksDao = bookDao;
        this.usersDao = usersDao;
        this.authorsDao = authorsDao;
        this.ownedBooksDao = ownedBooksDao;
    }
  
   @GetMapping("/books")
    public String ShowBooks(Model model){
        model.addAttribute("searched", false);
        model.addAttribute("allBooks", booksDao.findAll());
        return "/books/index";
    }

    @PostMapping("/books/search")
    public String searchBooks(@RequestParam(name = "query") String query, @RequestParam(name="param") String param, Model model){
        System.out.println(param);
        System.out.println(query);
        switch (param){
            case "title":
                model.addAttribute("allBooks", booksDao.searchByTitleLike(query));
                model.addAttribute("searchedBy", "Title: " + query);
                break;
            case "author":
                List<Author> authors = authorsDao.searchByFullnameLike(query);
                List<Book> allBooks = new ArrayList<>();
                for(Author author : authors){
                    List<Book> authorBooks = booksDao.searchByAuthor(author);
                    for(Book book : authorBooks)
                    allBooks.add(book);
                }
                model.addAttribute("allBooks", allBooks);
                model.addAttribute("searchedBy", "Author: " + query);
                break;
            default:
                System.out.println("Switch case fallthrough on PostMapping /books/search");
                break;
        }
        model.addAttribute("searchedparam", param);
        model.addAttribute("searchedquery", query);
        model.addAttribute("searched", true);
        return "/books/index";
    }
    @GetMapping("/books/search/api")
    public String searchBooksByApi(@RequestParam(name="param") String param, @RequestParam(name="query") String query, Model model){
        System.out.println(param);
        System.out.println(query);
        switch (param){
            case "title":
                model.addAttribute("allBooks", booksDao.searchByTitleLike(query));
                model.addAttribute("searchedBy", "Title: " + query);
                break;
            case "author":
                List<Author> authors = authorsDao.searchByFullnameLike(query);
                List<Book> allBooks = new ArrayList<>();
                for(Author author : authors){
                    List<Book> authorBooks = booksDao.searchByAuthor(author);
                    for(Book book : authorBooks)
                        allBooks.add(book);
                }
                model.addAttribute("allBooks", allBooks);
                model.addAttribute("searchedBy", "Author: " + query);
                break;
            default:
                System.out.println("Switch case fallthrough on PostMapping /books/search/api");
                break;
        }
        model.addAttribute("key1", GBKey);
        model.addAttribute("searchedparam", param);
        model.addAttribute("searchedquery", query);
        model.addAttribute("searched", true);

        return "/books/search";
    }
//    Create
    @GetMapping("/books/create")
    private String showCreateForm(@RequestParam(name="title") String title, @RequestParam(name="author") String author, @RequestParam(name="imagesrc") String imagesrc, @RequestParam(name="summary") String summary, Model model){
        model.addAttribute("title", title);
        model.addAttribute("author", author);
        model.addAttribute("imagesrc", imagesrc);
        model.addAttribute("summary", summary);
        return "/books/create";
    }

    @PostMapping("/books/create")
    private String submitCreateBookForm(@RequestParam(name="title") String title, @RequestParam(name = "author") String author, @RequestParam(name = "image") String image, @RequestParam(name = "summary") String summary, Model model){
        Book book = new Book();
        book.setTitle(title);
        book.setBookImg(image);
        book.setSummary(summary);
        if(authorsDao.findAuthorByFullname(author) == null){
            Author newAuthor = authorsDao.save(new Author(author));
            book.setAuthor(newAuthor);
        } else {
            book.setAuthor(authorsDao.findAuthorByFullname(author));
        }
        booksDao.save(book);   
        return "redirect:/books/"+book.getId();
    }
//    Read
    @GetMapping("/books/{id}")
    public String individualBook(@PathVariable long id, Model model){
        model.addAttribute("book", booksDao.getById(id));
        return "/books/show";
    }
//    Update
    @GetMapping("/books/{id}/edit")
    public String showUpdateForm(@PathVariable long id, Model model,@ModelAttribute Book book){
        Book editBook = booksDao.getById(id);
        model.addAttribute("editBook", editBook);
        return "/books/edit";
    }
    @PostMapping("/books/{id}/edit")
    public String submitUpdateForm(@PathVariable long id,@ModelAttribute Book book, Model model){
        book.setAuthor(booksDao.getById(id).getAuthor());
        booksDao.save(book);
        return "redirect:/books/" + book.getId();

    }

//    Delete
    @DeleteMapping("books/{id}/delete")
    private String deleteBook(@PathVariable long id){
            booksDao.deleteById(id);
            return "redirect:/books";
    }
}