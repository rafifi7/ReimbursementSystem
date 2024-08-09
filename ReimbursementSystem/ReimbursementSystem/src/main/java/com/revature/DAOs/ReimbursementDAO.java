package com.revature.DAOs;

import com.revature.Models.Reimbursement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReimbursementDAO extends JpaRepository<Reimbursement, Integer>{
    public List<Reimbursement> findByUserUserId(int userId);


}
