package com.revature.Services;

import com.revature.DAOs.UserDAO;
import com.revature.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDAO userDAO;


    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public User registerUser(User newUser) {
        User u = userDAO.save(newUser);
        return u;
    }


    public List<User> getAllUsers() {
        return userDAO.findAll();
    }

    public User getUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    public User updateUser(String newUsername, int userId) {
        Optional<User> existingUser = userDAO.findById(userId);

        if (existingUser.isPresent()) {
            User u = existingUser.get();
            u.setUsername(newUsername);
            return userDAO.save(u);
        } else {
            //probably throw an exception because this user does not exist
            return null;
        }
    }

    public User promoteUser(int userId) {
        return changeUserRole(userId, "Manager");
    }

    public User changeUserRole(int userId, String role) {
        Optional <User> existingUser = userDAO.findById(userId);

        if (existingUser.isPresent()) {
            User u = existingUser.get();
            if (u.getRole().equals(role)) {
                //throw an exception
                return null;
            }
            u.setRole(role);
            return userDAO.save(u);
        } else {
            //throw an exception eventually
            return null;
        }
    }



}
