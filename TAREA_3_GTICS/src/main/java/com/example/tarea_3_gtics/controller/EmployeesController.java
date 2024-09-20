package com.example.tarea_3_gtics.controller;

import com.example.tarea_3_gtics.entity.Department;
import com.example.tarea_3_gtics.entity.Employee;
import com.example.tarea_3_gtics.entity.Job;
import com.example.tarea_3_gtics.repository.DepartmentRepository;
import com.example.tarea_3_gtics.repository.JobRepository;
import com.example.tarea_3_gtics.repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;


@Controller
public class EmployeesController {

    final EmployeeRepository employeeRepository;
    final JobRepository jobRepository;
    final DepartmentRepository departmentRepository;
    //final EmployeeRepository2 employeeRepository2;

    public EmployeesController(EmployeeRepository employeeRepository, JobRepository jobRepository, DepartmentRepository departmentRepository /*, EmployeeRepository2 employeeRepository2*/) {
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
        this.departmentRepository = departmentRepository;
        //this.employeeRepository2 = employeeRepository2;
    }

    @GetMapping({"/","menu"})
    public String menu() {

        return "menu";
    }

    @GetMapping({"employee/list","employee"})
    public String listarEmployees(Model model) {

        List<Employee> listaEmployee = employeeRepository.findAll();
        model.addAttribute("employeesList", listaEmployee);


        return "listaEmpleados";
    }

    @GetMapping("employee/edit/{id}")
    public String informEmployee(@PathVariable("id") Integer employeeId, Model model) {
        Optional<Employee> oEmployee = employeeRepository.findById(employeeId);
        if(oEmployee.isPresent()) {
            Employee employee = oEmployee.get();
            model.addAttribute("employee", employee);
            model.addAttribute("selectedJobId",employee.getJob().getJob_id());
        }
        List<Employee> listaEmployee = employeeRepository.findAll();
        List<Job> listaJob = jobRepository.findAll();
        List<Department> listaDepartment = departmentRepository.findAll();
        model.addAttribute("employeesList", listaEmployee);
        model.addAttribute("jobList", listaJob);
        model.addAttribute("departmentList",listaDepartment);
        return "editarEmpleado";
    }

    @GetMapping("employee/delete/{id}")
    public String borrarEmployee(@PathVariable("id") Integer employeeId, Model model) {
        Optional<Employee> employeeXDDD = employeeRepository.findById(employeeId);
        List<Employee> listaEmployee = employeeRepository.findAll();

        if (employeeXDDD.isPresent()) {
            try {
                employeeRepository.deleteById(employeeId);
            } catch (Exception e) {
                model.addAttribute("employeesList", listaEmployee);
                model.addAttribute("errorMessage", "El empleado no puede ser borrado porque tiene relaciones dependientes.");
                return "listaEmpleados";
            }
        } else {
            model.addAttribute("employeesList", listaEmployee);
            model.addAttribute("errorMessage", "El empleado no existe.");
            return "listaEmpleados";
        }
        return "redirect:/employee";
    }

    @PostMapping("filtro")
    public String filtroEmpleado(@RequestParam("filtro") String filtro, Model model){
        List<Employee> listaEmployee = employeeRepository.findByFilter(filtro);
        model.addAttribute("employeesList", listaEmployee);
        model.addAttribute("filtro", filtro);
        return "listaEmpleados";
    }

    @GetMapping("nuevoEmpleado")
    public String nuevoEmpleado(Model model){
        List<Employee> listaEmployee = employeeRepository.findAll();
        List<Job> listaJob = jobRepository.findAll();
        List<Department> listaDepartment = departmentRepository.findAll();
        model.addAttribute("employeesList", listaEmployee);
        model.addAttribute("jobList", listaJob);
        model.addAttribute("departmentList",listaDepartment);
        return "nuevoEmpleado";
    }

    @PostMapping("/guardarEmpleado")
    public String NuevoEmpleado(Model model, Employee employee, @RequestParam("job_id") String jobId, @RequestParam("department_id") Integer departmentId) throws NoSuchAlgorithmException {
        employee.setEnabled(true);
        Optional<Job> optionalJob = jobRepository.findById(jobId);
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        Job job = optionalJob.orElse(null);
        Department department = optionalDepartment.orElse(null);
        employee.setJob(job);
        employee.setDepartment(department);
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String hire_date = String.valueOf(formatter);
        employee.setHire_date(String.valueOf(currentDateTime));
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(employee.getPassword().getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        String passwordHash = hexString.toString();
        employee.setPassword(passwordHash);
        Optional<Employee> optionalEmployee = Optional.of(employeeRepository.save(employee));
        List<Employee> listaEmployee = employeeRepository.findAll();
        model.addAttribute("employeesList", listaEmployee);
        model.addAttribute("mensajeCorrecto", "Empleado Creado Exitosamente");
        return "listaEmpleados";
    }

    @PostMapping("/actualizarEmpleado")
    public String actualizarEmpleado(Model model, Employee employee, @RequestParam("job_id") String jobId, @RequestParam("department_id") Integer departmentId) throws NoSuchAlgorithmException {
        employee.setEnabled(true);

        Optional<Job> optionalJob = jobRepository.findById(jobId);
        Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
        Job job = optionalJob.orElse(null);
        Department department = optionalDepartment.orElse(null);
        employee.setJob(job);
        employee.setDepartment(department);
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String hire_date = String.valueOf(formatter);
        employee.setHire_date(String.valueOf(currentDateTime));
        Optional<Employee> optionalEmployee = Optional.of(employeeRepository.save(employee));
        List<Employee> listaEmployee = employeeRepository.findAll();
        model.addAttribute("employeesList", listaEmployee);
        model.addAttribute("mensajeCorrecto", "Empleado Creado Exitosamente");
        return "listaEmpleados";
    }



}
