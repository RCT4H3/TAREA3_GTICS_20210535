package com.example.tarea_3_gtics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="job_history")
public class JobHistory {
    @Id
    @Column(name="job_history_id",nullable = false)
    private Integer job_history_id;

    @Column(name="start_date",nullable = false)
    private String start_date;

    @Column(name="end_date",nullable = false)
    private String end_date;

    @ManyToOne
    @JoinColumn(name="employee_id",nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name="job_id",nullable = false)
    private Job job;

    @ManyToOne
    @JoinColumn(name="department_id",nullable = false)
    private Department department;


}
