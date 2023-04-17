package com.spring.mvc.controller;

import com.spring.mvc.entity.Book;
import com.spring.mvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class AddBookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/addBook")
    public String addBookPage(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("/addBook")
    public String addNewBook(@ModelAttribute("book") @Valid Book book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addBook";
        }
        Book existingBook = bookService.getBookByIsbn(book.getIsbn());
        if(existingBook != null){
            // Book with same ISBN already exists in the database
            // You can throw an exception or show an error message
            model.addAttribute("error", "Book with same ISBN number already exists in the database");
            return "addBook";
        }
        bookService.addBook(book);
        return "redirect:/dashboard";
    }

}
