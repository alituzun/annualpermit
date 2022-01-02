package com.yk.annualpermit.rest;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yk.annualpermit.LeaveStatus;
import com.yk.annualpermit.repository.LeaveRepository;
import com.yk.annualpermit.service.ManagerService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/manager")
@Api(value = "Manager Rest Api")
public class ManagerRest {

    LeaveRepository leaveRepository;

    ManagerService managerService;


    Object obj;

    @Autowired
    public ManagerRest(LeaveRepository leaveRepository, ManagerService managerService) {
        this.leaveRepository = leaveRepository;
        this.managerService = managerService;
    }

    @GetMapping(value = "/leave")
    public ResponseEntity findPendingLeaves() {
        return ResponseEntity.ok(leaveRepository.findLeavesByStatus(String.valueOf(LeaveStatus.pending)));
    }

    @PostMapping(value = "/approve")
    public ResponseEntity approveLeave(@RequestParam @Valid @NotEmpty Long id) {

        managerService.approveLeaveRequest(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping(value = "/disapprove")
    public ResponseEntity disapproveLeave(@RequestParam @Valid @NotEmpty Long id) {

        managerService.disapproveLeaveRequest(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
