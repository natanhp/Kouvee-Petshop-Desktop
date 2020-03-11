package com.P3LJ2.P3L_J_2;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByName(String name);
    Employee findById(long Id);

    Employee getEmployeeId();
}
