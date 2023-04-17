package com.spring.mvc.service;

import com.spring.mvc.database.UserDatabase;
import com.spring.mvc.dto.LoginDTO;
import com.spring.mvc.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDatabase userDatabase;

    public List<User> getAllUsers(){
        return userDatabase.getAllUsers();
    }

    public User registerUser(User user){
        return userDatabase.registerUser(user);
    }

    public boolean validateUser(LoginDTO dto) throws Exception {
        return this.userDatabase.loginUser(dto);
    }
    public User getUserByEmail(String email){
        return this.userDatabase.getUserByEmail(email);
    }
}
