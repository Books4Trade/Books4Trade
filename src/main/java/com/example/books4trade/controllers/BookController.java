package com.example.books4trade.controllers;


import com.example.books4trade.models.Book;
import com.example.books4trade.models.OwnedBook;
import com.example.books4trade.models.User;
import com.example.books4trade.repositories.BookRepository;
import com.example.books4trade.repositories.OwnedBookRepository;
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
    private OwnedBookRepository ownedBooksDao;

    public BookController(BookRepository bookDao, UserRepository usersDao, OwnedBookRepository ownedBooksDao){
        this.booksDao = bookDao;
        this.usersDao = usersDao;
        this.ownedBooksDao = ownedBooksDao;
    }
  
   @GetMapping("/books")
    public String ShowBooks(Model model){
        model.addAttribute("allBooks", booksDao.findAll());
        return "/books/index";
    }
  
//    Create
    @GetMapping("/books/create")
    private String showCreateForm(Model model){
        model.addAttribute("book", new Book());
        return "/books/create";
    }

    @PostMapping("/books/create")
    private String submitCreateBookForm(@ModelAttribute Book book){
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




    @GetMapping("/book/{id}/edit")
    public String showUpdateForm(@PathVariable long id, Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//        Book book = booksDao.getById(id);
        OwnedBook ownedBook = ownedBooksDao.getById(id);
        if(user.getId() == ownedBook.getUser().getId())
        model.addAttribute("book", ownedBook);
        return "/books/edit";
    }

    @PostMapping("/book/{id}/edit")
    public String submitUpdateForm(@ModelAttribute Book book, Model model){
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
