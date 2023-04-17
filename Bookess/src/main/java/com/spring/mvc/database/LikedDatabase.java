package com.spring.mvc.database;

import com.spring.mvc.entity.LikedBooks;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class LikedDatabase {

    @Autowired
    private SessionFactory sessionFactory;
    public void addLikedBooks(LikedBooks likedBooks) {
        if (!likedBookExistsById(likedBooks.getId())) {
            Session session = sessionFactory.getCurrentSession();
            session.save(likedBooks);
        }
    }
    public boolean likedBookExistsById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "FROM LikedBooks WHERE id = :id";
        LikedBooks likedBooks = session.createQuery(sql, LikedBooks.class)
                .setParameter("id", id)
                .uniqueResult();
        return likedBooks != null;
    }

    public boolean likedBookExists(String isbn) {
        Session session = sessionFactory.getCurrentSession();
        String sql = "FROM LikedBooks WHERE isbn = :isbn";
        LikedBooks likedBooks = session.createQuery(sql, LikedBooks.class)
                .setParameter("isbn", isbn)
                .uniqueResult();
        return likedBooks != null;
    }

    public List<LikedBooks> getLikedBooks() {
        Session session = sessionFactory.getCurrentSession();
        String sql = "SELECT new com.spring.mvc.entity.LikedBooks(r.id, r.title, r.author, r.isbn, r.genre, r.description, r.rating, r.price, r.coverImage) FROM LikedBooks r";
        List<LikedBooks> likedBooks = session.createQuery(sql, LikedBooks.class)
                .getResultList();
        return likedBooks;
    }

    public void removeLikedBook(long bookId) {
        Session session = sessionFactory.getCurrentSession();
        LikedBooks likedBooks = session.get(LikedBooks.class, bookId);
        if (likedBooks != null) {
            session.delete(likedBooks);
        }
    }
}
