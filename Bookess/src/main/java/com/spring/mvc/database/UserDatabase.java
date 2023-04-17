package com.spring.mvc.database;

import com.spring.mvc.dto.LoginDTO;
import com.spring.mvc.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDatabase {
    @Autowired
    private SessionFactory factory;

    public List<User> getAllUsers(){
        Session session = factory.openSession();
        List<User> users = session.createQuery("from User", User.class).getResultList();
        session.close();
        return users;
    }

    public User registerUser(User user){
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        User existingUser = session.get(User.class,user.getEmail());
        if(existingUser!=null){
            tx.rollback();
            session.close();
            throw new RuntimeException("User with email " + user.getEmail() + " already exists");
        }
        session.save(user);
        tx.commit();
        session.close();
        return user;
    }

    public boolean loginUser(LoginDTO dto) throws Exception {
        System.out.println(dto);
        Session session = factory.openSession();
        User user = session.get(User.class, dto.getEmail());
        System.out.println(user);
        if(user == null) {
            session.close();
            throw new Exception("User not found");
        } else if(!dto.getPassword().equals(user.getPassword())) {
            session.close();
            throw new Exception("Incorrect password");
        }
        session.close();
        return true;
    }
    public User getUserByEmail(String email){
        Session session = factory.openSession();
        User user = session.get(User.class, email);
        session.close();
        return user;
    }

}
