package com.spring.mvc.service;

import com.spring.mvc.database.ReadLaterDatabase;
import com.spring.mvc.entity.Book;
import com.spring.mvc.entity.ReadLaterBooks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadLaterService {
    @Autowired
    private ReadLaterDatabase readLaterDatabase;
    public void addReadLaterBooks(ReadLaterBooks readLaterBooks) {
        this.readLaterDatabase.addReadLaterBooks(readLaterBooks);
    }
    public boolean ReadLaterBookExistsById(Long id) {
        return readLaterDatabase.ReadLaterBookExistsById(id);
    }
    public boolean bookExists(String isbn) {
        return readLaterDatabase.bookExists(isbn);
    }
    public List<ReadLaterBooks> getReadLaterBooks() {
        return readLaterDatabase.getReadLaterBooks();
    }
    public ReadLaterBooks getReadLaterBooksByIsbn(String isbn){
        return this.readLaterDatabase.getReadLaterBookByIsbn(isbn);
    }
}



