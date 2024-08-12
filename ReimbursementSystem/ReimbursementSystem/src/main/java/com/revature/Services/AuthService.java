package com.revature.Services;

import com.revature.DAOs.AuthDAO;
import com.revature.Models.LoginDTO;
import com.revature.Models.OutgoingUserDTO;
import com.revature.Models.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    //autowire the DAO
    private AuthDAO aDAO;

    @Autowired
    public AuthService(AuthDAO aDAO) {
        this.aDAO = aDAO;
    }

    //this method will take in a LoginDTO and return the User with those credentials (if exists)
    public OutgoingUserDTO login(LoginDTO lDTO, HttpSession session){

        User u = aDAO.findByUsernameAndPassword(lDTO.getUsername(), lDTO.getPassword());

        if(u == null){
            return null;
            //TODO: an Exception would be better
        } else {
            //if the user is not null (username/password pair match a user)...

            //Create an outgoing UserDTO and send it to the Controller
            OutgoingUserDTO outUser = new OutgoingUserDTO(u.getUserId(), u.getUsername(), u.getRole());

            //Initialize the HttpSession sent in from AuthController and give it values
            session.setAttribute("userId", u.getUserId());
            session.setAttribute("firstName", u.getFirstName());
            session.setAttribute("lastName", u.getLastName());
            session.setAttribute("username", u.getUsername());
            session.setAttribute("password", u.getPassword());
            session.setAttribute("role", u.getRole());
            //WHY STORE THESE?? plenty of reasons:
            //-uniquely identify the user in the backend logic and HTTP responses
            //ex: use the stored userId in "findBy" methods to clean up the URL
            //-verify a user's role before allowing them access to certain methods


            //send the user data back to the Controller -> FrontEnd
            return outUser;

        }

    }



}
