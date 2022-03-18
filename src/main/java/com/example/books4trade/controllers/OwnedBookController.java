package com.example.books4trade.controllers;

import com.example.books4trade.models.Book;
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
import org.springframework.web.bind.annotation.*;

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

        @GetMapping("/books/copies")
        public String showAllCopies(Model model){
                model.addAttribute("allBooks", ownedBooksDao.findAll());
                return "/owned-books/copies-index";
        }

        @GetMapping("/books/{id}/copies")
        public String showAllCopiesOfBook(@PathVariable long id, Model model){
                model.addAttribute("book", booksDao.getById(id));
                model.addAttribute("allBooks", ownedBooksDao.findOwnedBooksByBook(booksDao.getById(id)));
                return "/owned-books/copies-index";
        }

        @GetMapping("/books/{id}/copies/add")
        public String showCreateOwnedBook(@PathVariable long id, Model model) {
                model.addAttribute("book", booksDao.getById(id));
                return"/owned-books/create-copy";
        }

        @PostMapping("/books/{id}/copies/add")
        public String submitCreateOwnedBook(@PathVariable long id, @RequestParam(name = "bookCondition") String bookCondition, @RequestParam(name = "isTradeable") boolean isTradeable, @RequestParam(name = "bookType") long bookType, Model model){
                User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                User user = usersDao.findByUsername(currentUser.getUsername());
                OwnedBook newCopy = new OwnedBook(bookCondition, isTradeable, typesDao.getById(bookType), user, booksDao.getById(id));
                OwnedBook createdCopy = ownedBooksDao.save(newCopy);
                return "redirect:/books/"+id+"/copies/"+createdCopy.getId();
        }


        @GetMapping("/books/{id}/copies/{copyid}")
        public String showOneOwnedBook(@PathVariable long id, @PathVariable long copyid, Model model){
                User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                OwnedBook showBook = ownedBooksDao.getById(copyid);
                boolean isOwner = false;
                if(showBook.getUser().getId() == currentUser.getId()){
                        isOwner = true;
                }
                model.addAttribute("isOwner", isOwner);
                model.addAttribute("book", booksDao.getById(id));
                model.addAttribute("showBook", showBook);
                return "/owned-books/show";
        }

//        @GetMapping("/")


        @DeleteMapping("/books/{id}/copies/{copyid}/delete")
        public String deletePost(@PathVariable long id, @PathVariable long copyid){
                User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                OwnedBook copyToDelete = ownedBooksDao.getById(copyid);
                if(copyToDelete.getUser().getId() == currentUser.getId()) {
                        ownedBooksDao.delete(copyToDelete);
                }
                return "redirect:/books/copies";
        }



}


