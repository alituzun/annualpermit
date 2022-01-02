package com.yk.annualpermit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yk.annualpermit.model.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Long> {

        Leave findLeavesByStatus(String status);

}
