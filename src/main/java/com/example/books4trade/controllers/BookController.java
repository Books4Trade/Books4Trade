package com.example.books4trade.controllers;


import com.example.books4trade.models.Book;
import com.example.books4trade.models.User;
import com.example.books4trade.repositories.BookRepository;
import com.example.books4trade.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
public class BookController {
    private BookRepository booksDao;
    private UserRepository usersDao;

    public BookController(BookRepository bookDao, UserRepository usersDao){
        this.booksDao = bookDao;
        this.usersDao = usersDao;
    }
  
   @GetMapping("/books")
    public String ShowBooks(Model model){
        model.addAttribute("allBooks", booksDao.findAll());
        return "books/index";
    }
  
//    Create
    @GetMapping("/books/create")
    private String showCreateForm(Model model){
        model.addAttribute("newBook", new Book());
        return "books/create";
    }

    @PostMapping("books/create")
    private String submitCreateBookForm(@ModelAttribute Book newBook){
        booksDao.save(newBook);
        return "/books";
    }


//    Read

    @GetMapping("/books/{id}")
    public String individualBook(@PathVariable long id, Model model){
        model.addAttribute("singleBook", booksDao.getById(id));
        return "books/show";
    }

//    Update
//    show form

    @GetMapping("/books/{id}/edit")
    public String showUpdateForm(@PathVariable long id, Model model){
        Book book = booksDao.getById(id);
        model.addAttribute("book", book);
        return "books/create";
    }

    @PostMapping("/books/{id}/edit")
    public String submitUpdateForm(@ModelAttribute Book book, Model model){
        booksDao.save(book);
        return "redirect:books/" + book.getId();
    }

//    Delete
    @DeleteMapping("books/{id}/delete")
    private String deleteBook(@PathVariable long id){
            booksDao.deleteById(id);
            return "redirect:/books";
    }


}
