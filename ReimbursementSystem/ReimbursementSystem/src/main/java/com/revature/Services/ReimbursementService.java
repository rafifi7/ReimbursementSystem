package com.revature.Services;

import com.revature.DAOs.ReimbursementDAO;
import com.revature.DAOs.UserDAO;
import com.revature.Models.IncomingReimbursementDTO;
import com.revature.Models.Reimbursement;
import com.revature.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReimbursementService {
    private ReimbursementDAO rDAO;
    private UserDAO uDAO;

    @Autowired
    public ReimbursementService(ReimbursementDAO rDAO, UserDAO uDAO) {
        this.rDAO = rDAO;
        this.uDAO = uDAO;
    }

    public Reimbursement addReimbursement(IncomingReimbursementDTO newReimbursement) {
        Reimbursement reimbursement = new Reimbursement(0, newReimbursement.getDescription(),
                newReimbursement.getAmount(), newReimbursement.getStatus(), null);
        Optional<User> u = uDAO.findById(newReimbursement.getUserId());

        if (u.isPresent()) {
            reimbursement.setUser(u.get());
            Reimbursement r = rDAO.save(reimbursement);
            return r;
        } else {
            return null;
        }


        /*Optional?? What's going on here?

         findById returns an Optional<Datatype>. What's the point?
         Optionals are often used to prevent NullPointerExceptions. The data may or may not exist
         In other words, the presence of our data is OPTIONAL.
         We can check if the value is present with .isPresent() method, and extract it with .get() */

    }

    public List<Reimbursement> getAllReimbursements() {
        return rDAO.findAll();
    }

    public List<Reimbursement> getReimbursementsByUserId(int userId) {
        return rDAO.findByUserUserId(userId);
    }

    public void deleteReimbursementById(int id) {
        //get reimbursement obj by id
        Reimbursement reimbursement = rDAO.findById(id).get();
        // delete from user's reimbursements list
        reimbursement.getUser().getReimbursements().remove(reimbursement);
        // delete reimbursement
        rDAO.deleteById(id);
    }



}