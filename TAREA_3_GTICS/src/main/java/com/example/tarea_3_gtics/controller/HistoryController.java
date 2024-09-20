package com.example.tarea_3_gtics.controller;
import com.example.tarea_3_gtics.entity.Employee;
import org.springframework.ui.Model;
import com.example.tarea_3_gtics.entity.JobHistory;
import com.example.tarea_3_gtics.repository.JobHistoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HistoryController {

    final JobHistoryRepository jobHistoryRepository;

    public HistoryController(JobHistoryRepository jobHistoryRepository) {
        this.jobHistoryRepository = jobHistoryRepository;
    }

    @GetMapping("historial")
    public String listarHistorial(Model model){
        List<JobHistory> historial = jobHistoryRepository.findAll();
        model.addAttribute("historialLista", historial);
        return "historial";
    }

    @PostMapping("filtroHistorial")
    public String filtroHistorial(Model model, @RequestParam("filtro") String filtro){

        List<JobHistory> historial = jobHistoryRepository.findByFilter(filtro);
        model.addAttribute("historialLista", historial);
        model.addAttribute("filtro", filtro);
        return "historial";
    }

    @GetMapping({"menuReportes"})
    public String menu() {
        return "menu2";
    }

    @GetMapping("employeeMaxSalary")
    public String maxSalary(Model model){
        List<JobHistory> jobHs = jobHistoryRepository.findEmployeesWithHighSalary();
        model.addAttribute("jobHs", jobHs);
        return "employeeMaxSalary";
    }

    @GetMapping("departmentsByCityNCountries")
    public String departmentsByCityNCountries(Model model){

        return "departmentsByCityNCountries";
    }

    @GetMapping("ManagerWithExperience")
    public String ManagerWithExperience(Model model){
        return "ManagerWithExperience";
    }
}
