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

        @GetMapping("/copies/all")
        public String showAllCopies(Model model){
                model.addAttribute("individual", false);
                model.addAttribute("allBooks", ownedBooksDao.findAll());
                return "owned-books/copies-index";
        }

        @GetMapping("/books/{id}/copies")
        public String showAllCopiesOfBook(@PathVariable long id, Model model){
                model.addAttribute("individual", true);
                model.addAttribute("book", booksDao.getById(id));
                model.addAttribute("allBooks", ownedBooksDao.findOwnedBooksByBook(booksDao.getById(id)));
                return "owned-books/copies-index";
        }

        @GetMapping("/books/{id}/addcopy")
        public String showCreateOwnedBook(@PathVariable long id, Model model) {
                model.addAttribute("book", booksDao.getById(id));
                return"owned-books/create-copy";
        }

        @PostMapping("/books/{id}/addcopy")
        public String submitCreateOwnedBook(@PathVariable long id, @RequestParam(name = "bookCondition") String bookCondition, @RequestParam(name = "isTradeable") boolean isTradeable, @RequestParam(name = "bookType") long bookType, Model model){
                User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                User user = usersDao.findByUsername(currentUser.getUsername());
                OwnedBook newCopy = new OwnedBook(bookCondition, isTradeable, typesDao.getById(bookType), user, booksDao.getById(id));
                OwnedBook createdCopy = ownedBooksDao.save(newCopy);
                return "redirect:/copies/"+createdCopy.getId();
        }


        @GetMapping("/copies/{copyid}")
        public String showOneOwnedBook(@PathVariable long copyid, Model model){
                User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                OwnedBook ownedBook = ownedBooksDao.getById(copyid);
                boolean isOwner = false;
                if(ownedBook.getUser().getId() == currentUser.getId()){
                        isOwner = true;
                }
                model.addAttribute("isOwner", isOwner);
               // model.addAttribute("book", booksDao.getById(id));
                model.addAttribute("ownedBook", ownedBook);
                return "owned-books/show";
        }

//        @GetMapping("/")
        @GetMapping("/copies/{copyid}/edit")
        public String showCopyEditForm( @PathVariable(name="copyid") long copyid, Model model){

                // ADD CHECK FOR CURRENT USER AS OWNER ELSE REDIRECT
                User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                OwnedBook ownedBook = ownedBooksDao.getById(copyid);
                model.addAttribute("ownedbook", ownedBook);
                return "owned-books/edit";
        }

        @PostMapping("/copies/{copyid}/edit")
        public String submitCopyEditForm( @PathVariable(name="copyid") long copyid, @RequestParam(name="bookcondition") String bookcondition, @RequestParam(name="istradeable") boolean istradeable, @RequestParam(name="booktype") long typeid){
                User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                User user = usersDao.findById(currentUser.getId());
                OwnedBook editedBook = ownedBooksDao.findById(copyid);
                if(editedBook.getUser().getId() == user.getId()){
                        editedBook.setBookCondtion(bookcondition);
                        editedBook.setTradable(istradeable);
                        editedBook.setType(typesDao.findById(typeid));
                        OwnedBook saved = ownedBooksDao.save(editedBook);
                        return "redirect:/copies/"+saved.getId();
                }
                return "redirect:/copies";
        }

        @PostMapping("/copies/{id}/delete")
        public String deleteCopy(@PathVariable long id){
                User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                User user = usersDao.findById(currentUser.getId());
                System.out.println("Owned Book Delete attempt for Copy:"+id+", by User:"+user.getId());
               OwnedBook copyToDelete = ownedBooksDao.findById(id);
               if(copyToDelete.getUser().getId() == user.getId()) {
                        ownedBooksDao.delete(copyToDelete);
                        System.out.println("OwnedBook Deleted:" + id);
                }
                return "redirect:/books";
        }
}


