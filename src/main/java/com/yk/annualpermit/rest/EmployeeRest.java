package com.yk.annualpermit.rest;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yk.annualpermit.repository.EmployeeRepository;
import com.yk.annualpermit.service.LeaveService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/employee")
@Api(value = "Employee Rest Api")
@Validated
public class EmployeeRest {

    EmployeeRepository repository;

    LeaveService service;

    @Autowired
    public EmployeeRest(EmployeeRepository repository, LeaveService service) {
        this.repository = repository;
        this.service = service;
    }

    @PostMapping(value = "/leave")
    public ResponseEntity newLeave(@RequestParam  @NotEmpty String tckn,
                                   @RequestParam("startDate") @DateTimeFormat(pattern = "dd.MM.yyyy") Date startDate,
                                   @RequestParam("endDate") @DateTimeFormat(pattern = "dd.MM.yyyy") Date endDate) throws ParseException {

        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);



        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        return ResponseEntity.ok(service.requestLeave(tckn,startCal,endCal));

    }

    @GetMapping(value = "/leave")
    public ResponseEntity findLeave(@RequestParam @Valid @NotEmpty @Size(min = 11,max=11) String tckn){
        return ResponseEntity.ok(repository.findEmployeeByTckn(tckn).getLeaveList());
    }
}