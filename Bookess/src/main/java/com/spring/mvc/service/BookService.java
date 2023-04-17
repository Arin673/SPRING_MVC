package com.spring.mvc.service;

import com.spring.mvc.database.BookDatabase;
import com.spring.mvc.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookDatabase bookDatabase;

    public void addBook(Book book) {
        this.bookDatabase.addBook(book);
    }
    public Book loadBook(long bookid){
        return this.bookDatabase.loadBook(bookid);
    }

    public Book getBookByIsbn(String isbn) {
        return bookDatabase.getBookByIsbn(isbn);
    }
    public Book getBookById(long bookId){
        Book book =  this.bookDatabase.getBookById(bookId);
        System.out.println("service "+book);
        return book;
    }

    public List<Book> getAllBooks() {
        return bookDatabase.getAllBooks();
    }

    public boolean updateBook(Book book) {
        return bookDatabase.updateBook(book);
    }

    public void deleteBook(Long id) {
        bookDatabase.deleteBook(id);
    }

    public Book findByIsbn(String isbn) {
        return this.bookDatabase.findByIsbn(isbn);
    }
}
