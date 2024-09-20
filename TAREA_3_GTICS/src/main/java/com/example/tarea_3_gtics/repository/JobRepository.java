package com.example.tarea_3_gtics.repository;


import com.example.tarea_3_gtics.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface JobRepository extends JpaRepository<Job, String>{

}

