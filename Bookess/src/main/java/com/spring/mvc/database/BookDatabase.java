package com.spring.mvc.database;

import com.spring.mvc.entity.Book;
import com.spring.mvc.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookDatabase {
    @Autowired
    private SessionFactory sessionFactory;

    public void addBook(Book book) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Book existingBook = getBookByIsbn(book.getIsbn());
            if (existingBook != null) {
                System.out.println("Book with same ISBN number already exists in the database");
            } else {
                session.save(book);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public Book getBookById(long bookId){
        System.out.println("Book DB" +bookId);
        Session session = sessionFactory.openSession();
        Book book = session.get(Book.class, bookId);
        session.close();
        System.out.println(book);
        return book;
    }
    public Book loadBook(long bookId) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.load(Book.class, bookId);
        session.close();
        return book;
    }

    public Book getBookByIsbn(String isbn) {
        Session session = sessionFactory.getCurrentSession();
        System.out.println("Get Book by ISBN");
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = builder.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("isbn"), isbn));
        Query query = session.createQuery(criteria);
        List<Book> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }

    public List<Book> getAllBooks() {

        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = builder.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        criteria.select(root);
        Query query = session.createQuery(criteria);
        return query.getResultList();
    }

    public boolean updateBook(Book book) {
        Session session = sessionFactory.getCurrentSession();
        session.update(book);
        return true;
    }

    public void deleteBook(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Book book = session.get(Book.class, id);
        session.delete(book);
    }

    public Book findByIsbn(String isbn) {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Book> criteria = builder.createQuery(Book.class);
        Root<Book> root = criteria.from(Book.class);
        criteria.select(root);
        criteria.where(builder.equal(root.get("isbn"), isbn));
        Query query = session.createQuery(criteria);
        List<Book> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        }
        return results.get(0);
    }

}
