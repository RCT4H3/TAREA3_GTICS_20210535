package com.example.tarea_3_gtics.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="employee_id",nullable = false)
    private Integer employee_id;

    @Column(name="first_name",length = 20)
    private String first_name;

    @Column(name="last_name",length = 25)
    private String last_name;

    @Column(name="email",length = 25)
    private String email;

    @Column(name="password",length = 25)
    private String password;

    @Column(name="phone_number",length = 25)
    private String phone_number;

    @Column(name="hire_date",length = 25)
    private String hire_date;


    @Column(name="salary",length = 25)
    private Double salary;

    @Column(name="manager_id",length = 25)
    private Integer manager_id;


    @Column(name="enabled",length = 25)
    private Boolean enabled;


    @ManyToOne
    @JoinColumn(name="job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;


}
