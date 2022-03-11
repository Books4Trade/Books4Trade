package com.example.books4trade.controllers;

import com.example.books4trade.models.OwnedBook;
import com.example.books4trade.models.Type;
import com.example.books4trade.models.User;
import com.example.books4trade.repositories.BookRepository;
import com.example.books4trade.repositories.OwnedBookRepository;
import com.example.books4trade.repositories.TypeRepository;
import com.example.books4trade.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OwnedBookController {

        private final UserRepository usersDao;
        private final TypeRepository typesDao;
        private final BookRepository booksDao;
        private final OwnedBookRepository ownedBooksDao;

        public OwnedBookController(UserRepository usersDao, TypeRepository typesDao, BookRepository booksDao, OwnedBookRepository ownedBooksDao) {
                this.usersDao = usersDao;
                this.typesDao = typesDao;
                this.booksDao = booksDao;
                this.ownedBooksDao = ownedBooksDao;
        }

        @GetMapping("/books/{id}/copy/add")
        public String showCreateOwnedBook(@PathVariable long id, Model model) {
                model.addAttribute("book", booksDao.findById(id));
                return"/owned-book/create-copy";
        }

        @PostMapping("/books/{id}/copy/add")
        public String submitCreateOwnedBook(@PathVariable long id, @RequestParam(name = "bookCondition") String bookCondition, @RequestParam (name = "isTradeable") boolean isTradeable, @RequestParam (name = "bookType") Type bookType ) {
                OwnedBook ownedBook = new OwnedBook();
                ownedBook.setBookOwned(booksDao.getById(id));
                ownedBook.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
                ownedBook.setBookCondtion(bookCondition);
                ownedBook.setTradable(isTradeable);
                ownedBook.setType(bookType);
                return "redirect:/owned-book";
        }
        @GetMapping("/books/{id}/copy")
        public String showAllOwnedCopies(@PathVariable long id, Model model){
                model.addAttribute("allBooks",)
                return "books/index";
        }

        @GetMapping("/books/{id}/copy/{copyid}")
        public String showOwnedBook(@PathVariable long id, @PathVariable long copyid, Model model){
                return "/owned-book/show";
        }



}


