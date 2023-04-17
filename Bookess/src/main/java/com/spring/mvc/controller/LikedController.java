package com.spring.mvc.controller;

import com.spring.mvc.entity.Book;
import com.spring.mvc.entity.LikedBooks;
import com.spring.mvc.service.BookService;
import com.spring.mvc.service.LikedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LikedController {

    @Autowired
    private LikedService likedService;

    @Autowired
    private BookService bookService;

    @GetMapping("/liked")
    public String showLikedBooks(Model model) {
        List<LikedBooks> likedBooks = likedService.getLikedBooks();
        model.addAttribute("liked", likedBooks);
        return "liked";
    }

    @PostMapping("/liked")
    public String addPostLikedBook(@RequestParam("bookId") Long bookId, Model model) {
        System.out.println("id "+bookId);
        if (!likedService.likedBookExistsById(bookId)) {
            Book book = bookService.getBookById(bookId);
            System.out.println("Controller "+book);
            if (book != null) {
                LikedBooks likedBooks = new LikedBooks();
                likedBooks.setTitle(book.getTitle());
                likedBooks.setAuthor(book.getAuthor());
                likedBooks.setIsbn(book.getIsbn());
                likedBooks.setGenre(book.getGenre());
                likedBooks.setDescription(book.getDescription());
                likedBooks.setRating(book.getRating());
                likedBooks.setPrice(book.getPrice());
                likedBooks.setCoverImage(book.getCoverImage());

                try {
                    likedService.addLikedBooks(likedBooks);
                    return "redirect:/liked";
                } catch (Exception e) {
                    model.addAttribute("errorMessage", "Cannot add book to liked list: " + e.getMessage());
                }
            } else {
                model.addAttribute("errorMessage", "Book not found");
            }
        } else {
            model.addAttribute("errorMessage", "Book already liked");
        }

        return "redirect:/error";
    }
}
