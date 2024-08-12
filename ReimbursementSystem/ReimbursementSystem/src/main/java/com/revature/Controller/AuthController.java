package com.revature.Controller;

import com.revature.Models.LoginDTO;
import com.revature.Models.OutgoingUserDTO;
import com.revature.Services.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/auth")
//set crossorigin to allow origin from localhost:3000, and allow credentials
@CrossOrigin(origins="http://localhost:3000", allowCredentials = "true")
public class AuthController {


    //Autowire AuthService
    private AuthService as;

    @Autowired
    public AuthController(AuthService as) {
        this.as = as;
    }

    //NOTE: our HTTP Session is coming in via parameters this time (to be sent to the Service)
    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO lDTO, HttpSession session){

        OutgoingUserDTO outUser = as.login(lDTO, session);

        //null check, this would be a try/catch if we had better handling in the service
        if(outUser == null){
            return ResponseEntity.status(401).body("Invalid Credentials! Try again");
        }
        return ResponseEntity.accepted().body(outUser);
    }
}