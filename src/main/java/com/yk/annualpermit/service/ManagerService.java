package com.yk.annualpermit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yk.annualpermit.LeaveStatus;
import com.yk.annualpermit.model.Leave;
import com.yk.annualpermit.repository.LeaveRepository;

@Service
public class ManagerService {

    LeaveRepository repository;

    @Autowired
    public ManagerService(LeaveRepository repository) {
        this.repository = repository;
    }

    public void approveLeaveRequest(Long id){

     Leave leave=repository.findById(id).get();

     leave.setStatus(String.valueOf(LeaveStatus.approved));

     repository.save(leave);

    }

    public void disapproveLeaveRequest(Long id){

        Leave leave=repository.findById(id).get();

        leave.setStatus(String.valueOf(LeaveStatus.disapproved));

        repository.save(leave);

    }

}
