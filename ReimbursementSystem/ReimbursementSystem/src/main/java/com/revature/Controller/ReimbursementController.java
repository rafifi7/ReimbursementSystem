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
@CrossOrigin
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

    @GetMapping("/pending")
    public ResponseEntity<List<Reimbursement>> getAllPendingReimbursements() {
        return ResponseEntity.ok(rs.getAllPendingReimbursements());
    }


    @GetMapping("/pending/{userId}")
    public ResponseEntity<List<Reimbursement>> getAllPendingReimbursementsByUserId(@PathVariable int userId) {
        return ResponseEntity.ok(rs.getAllPendingReimbursementsByUserId(userId));
    }

    @PostMapping("/updateDescription/{reimbursementId}")
    public ResponseEntity<Object> updateReimbursementDescription(
            @RequestBody String description,
            @PathVariable int reimbursementId) {

        Reimbursement updatedReimbursement = rs.updateReimbursementDescription(description, reimbursementId);
        if (updatedReimbursement == null) {
            return ResponseEntity.status(404).body("Reimbursement not found with ID: " + reimbursementId);
        }
        return ResponseEntity.ok(updatedReimbursement);
    }

    @PostMapping("/updateStatus/{reimbursementId}")
    public ResponseEntity<Object> updateReimbursementStatus(
            @RequestBody String status,
            @PathVariable int reimbursementId) {

        Reimbursement updatedReimbursement = rs.updateReimbursementStatus(status, reimbursementId);
        if (updatedReimbursement == null) {
            return ResponseEntity.status(404).body("Reimbursement not found with ID: " + reimbursementId);
        }
        return ResponseEntity.ok(updatedReimbursement);
    }


}
