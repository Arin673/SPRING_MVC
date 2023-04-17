package com.spring.mvc.controller;

import com.spring.mvc.entity.Book;
import com.spring.mvc.entity.LikedBooks;
import com.spring.mvc.entity.ReadLaterBooks;
import com.spring.mvc.service.BookService;
import com.spring.mvc.service.ReadLaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ReadLaterController {
    @Autowired
    private BookService bookService;
    @Autowired
    private ReadLaterService readLaterService;

    @GetMapping("/readLater")
    public String showReadLaterBooks(Model model) {
        List<ReadLaterBooks> readLaterBooks = readLaterService.getReadLaterBooks();
        model.addAttribute("readLater", readLaterBooks);
        return "readLater";
    }


    @PostMapping("/readLater")
    public String addPostLikedBook(@RequestParam("bookId") Long bookId, Model model)
    {
        System.out.println("id "+bookId);

        if (!readLaterService.ReadLaterBookExistsById(bookId))
        {
            Book book = bookService.getBookById(bookId);
            System.out.println("Controller "+book);

            if (book != null) {
                ReadLaterBooks readLaterBooks = new ReadLaterBooks();
                readLaterBooks.setTitle(book.getTitle());
                readLaterBooks.setAuthor(book.getAuthor());
                readLaterBooks.setIsbn(book.getIsbn());
                readLaterBooks.setGenre(book.getGenre());
                readLaterBooks.setDescription(book.getDescription());
                readLaterBooks.setRating(book.getRating());
                readLaterBooks.setPrice(book.getPrice());
                readLaterBooks.setCoverImage(book.getCoverImage());

                try {
                    readLaterService.addReadLaterBooks(readLaterBooks);
                    return "redirect:/readLater";
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
