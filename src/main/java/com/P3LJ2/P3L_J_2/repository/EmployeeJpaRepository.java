package com.P3LJ2.P3L_J_2.repository;

import com.P3LJ2.P3L_J_2.model.Employees;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class EmployeeJpaRepository {

    @PersistenceContext
    EntityManager entityManager;

    public Employees finById(int id)
    {
        return entityManager.find(Employees.class, id);
    }
}
