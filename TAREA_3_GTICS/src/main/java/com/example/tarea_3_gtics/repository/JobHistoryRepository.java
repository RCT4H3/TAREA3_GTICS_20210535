package com.example.tarea_3_gtics.repository;

import com.example.tarea_3_gtics.entity.Employee;
import com.example.tarea_3_gtics.entity.Job;
import com.example.tarea_3_gtics.entity.JobHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobHistoryRepository extends JpaRepository<JobHistory, String> {
    @Query(nativeQuery = true, value = "SELECT jh.* FROM job_history jh " +
            "JOIN employees e ON jh.employee_id = e.employee_id " +
            "JOIN jobs j ON jh.job_id = j.job_id " +
            "JOIN departments d ON jh.department_id = d.department_id " +
            "WHERE LOWER(e.first_name) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
            "OR LOWER(e.last_name) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
            "OR LOWER(j.job_title) LIKE LOWER(CONCAT('%', :filtro, '%')) " +
            "OR LOWER(d.department_name) LIKE LOWER(CONCAT('%', :filtro, '%'))")
    List<JobHistory> findByFilter(String filtro);

    @Query(nativeQuery = true, value = "SELECT jh.* FROM job_history jh " +
            "JOIN employees e ON jh.employee_id = e.employee_id " +
            "JOIN jobs j ON jh.job_id = j.job_id " +
            "WHERE e.salary > 15000")
    List<JobHistory> findEmployeesWithHighSalary();
}
