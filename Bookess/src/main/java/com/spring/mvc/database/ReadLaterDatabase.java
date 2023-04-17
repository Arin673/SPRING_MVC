package com.spring.mvc.database;

import com.spring.mvc.entity.LikedBooks;
import com.spring.mvc.entity.ReadLaterBooks;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ReadLaterDatabase {
    @Autowired
    private SessionFactory factory;

    public void addReadLaterBooks(ReadLaterBooks readLater) {
        if (!bookExists(readLater.getIsbn())) {
            try {
                Session session = factory.getCurrentSession();
                session.save(readLater);
            } catch (Exception e) {
                throw new RuntimeException("Cannot add to readLater books", e);
            }
        } else {
            System.out.println("Book already added to readLater list.");
        }
    }
    public boolean ReadLaterBookExistsById(Long id) {
        Session session = factory.getCurrentSession();
        String sql = "FROM ReadLaterBooks WHERE id = :id";
        ReadLaterBooks readLaterBooks = session.createQuery(sql, ReadLaterBooks.class)
                .setParameter("id", id)
                .uniqueResult();
        return readLaterBooks != null;
    }
    public boolean bookExists(String isbn) {
        Session session = factory.getCurrentSession();
        String sql = "FROM ReadLaterBooks WHERE isbn = :isbn";
        ReadLaterBooks readLater = session.createQuery(sql, ReadLaterBooks.class)
                .setParameter("isbn", isbn)
                .uniqueResult();
        return readLater != null;
    }

    public List<ReadLaterBooks> getReadLaterBooks() {
        Session session = factory.getCurrentSession();
        String sql = "SELECT new com.spring.mvc.entity.ReadLaterBooks(r.id, r.title, r.author, r.isbn, r.genre, r.description, r.rating, r.price, r.coverImage) FROM ReadLaterBooks r";
        List<ReadLaterBooks> readLaterBooks = session.createQuery(sql, ReadLaterBooks.class)
                .getResultList();
        return readLaterBooks;
    }

    public ReadLaterBooks getReadLaterBookByIsbn(String isbn) {
        Session session = factory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<ReadLaterBooks> cq = cb.createQuery(ReadLaterBooks.class);
        Root<ReadLaterBooks> root = cq.from(ReadLaterBooks.class);
        cq.select(root).where(cb.equal(root.get("isbn"), isbn));
        TypedQuery<ReadLaterBooks> query = session.createQuery(cq);
        List<ReadLaterBooks> results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else {
            return results.get(0);
        }
    }
}
