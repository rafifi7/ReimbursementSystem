package com.revature.Services;

import com.revature.DAOs.ReimbursementDAO;
import com.revature.DAOs.UserDAO;
import com.revature.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDAO userDAO;
    private ReimbursementDAO reimbursementDAO;


    @Autowired
    public UserService(UserDAO userDAO, ReimbursementDAO reimbursementDAO) {
        this.userDAO = userDAO;
        this.reimbursementDAO = reimbursementDAO;
    }


    public User registerUser(User newUser) {
        if (userDAO.findByUsername(newUser.getUsername()) != null) {
            System.out.println("Username already Exists!");
            return null;
        } else {
            User u = userDAO.save(newUser);
            return u;
        }
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

    public User deleteUser(int userId) {
        Optional<User> userOptional = userDAO.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Delete all user's reimbursements
            reimbursementDAO.deleteAll(user.getReimbursements());

            // Delete user
            userDAO.deleteById(userId);

            return user;
        } else {
            // Handle user not found (e.g., throw exception)
            return null;
        }
    }




}
