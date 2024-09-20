package com.example.tarea_3_gtics.repository;


import com.example.tarea_3_gtics.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    @Query(nativeQuery = true, value = "SELECT e.* FROM employees e " +
            "JOIN jobs j ON e.job_id = j.job_id " +
            "JOIN departments d ON e.department_id = d.department_id " +
            "JOIN locations l ON d.location_id = l.location_id " +
            "WHERE LOWER(e.first_name) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
            "OR LOWER(e.last_name) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
            "OR LOWER(j.job_title) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
            "OR LOWER(d.department_name) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
            "OR LOWER(l.city) LIKE LOWER(CONCAT('%', :filtro, '%'))")
    List<Employee> findByFilter(String filtro);
}

