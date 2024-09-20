package com.example.tarea_3_gtics.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="jobs")

public class Job {
    @Id
    @Column(name="job_id",nullable=false,length=10)
    private String job_id;

    @Column(name="job_title",nullable=false,length=35)
    private String job_title;

}
