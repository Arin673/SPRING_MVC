package com.spring.mvc.controller;

import com.spring.mvc.entity.Book;
import com.spring.mvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String showAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        System.out.println(books);
        return "index";
    }

}
