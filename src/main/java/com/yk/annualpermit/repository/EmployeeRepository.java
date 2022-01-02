package com.yk.annualpermit.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yk.annualpermit.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    Employee findEmployeeByTckn(String tckn);

}
