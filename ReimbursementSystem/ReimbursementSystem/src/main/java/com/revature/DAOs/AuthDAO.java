package com.revature.DAOs;

import com.revature.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthDAO extends JpaRepository<User, Integer> {

    //we just need the Auth layer for login purposes. could've also thrown register stuff in here

    //to verify that a user's login credentials are valid
    //we check for users where username = ? and password = ?
    public User findByUsernameAndPassword(String username, String password);

    //used to account for duplicate username account creation
    public User findByUsername(String username);

}
