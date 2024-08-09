package com.revature.Controller;

import com.revature.DAOs.UserDAO;
import com.revature.Models.IncomingReimbursementDTO;
import com.revature.Models.Reimbursement;
import com.revature.Services.ReimbursementService;
import com.revature.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reimbursements")
public class ReimbursementController {
    private ReimbursementService rs;
    private UserDAO uDAO;
    private UserService us;

    @Autowired
    public ReimbursementController(ReimbursementService rs, UserDAO uDAO, UserService us) {
        this.rs = rs;
        this.uDAO = uDAO;
        this.us = us;
    }

    @PostMapping
    public ResponseEntity<Object> addReimbursement(@RequestBody IncomingReimbursementDTO newReimbursement) {
        Reimbursement r = rs.addReimbursement(newReimbursement);
        if (r == null)
            return ResponseEntity.status(400).body("Couldn't find User with ID: " + newReimbursement.getUserId());

        return ResponseEntity.status(201).body(r);
    }

    @GetMapping
    public ResponseEntity<List<Reimbursement>> getAllReimbursements() {
        return ResponseEntity.ok(rs.getAllReimbursements());
    }

    @DeleteMapping("/{reimbId}")
    public ResponseEntity<Object> deleteReimbursementById(@PathVariable int reimbId) {


        rs.deleteReimbursementById(reimbId);
        return ResponseEntity.ok("Reimbursement with ID: " + reimbId + " was deleted");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Reimbursement>> getReimbursementsByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(rs.getReimbursementsByUserId(userId));
    }




}
