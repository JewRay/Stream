package com.stream.stream.controller;


import com.stream.stream.exceptions.EmployeeAlreadyAddedException;
import com.stream.stream.exceptions.EmployeeNotFoundException;
import com.stream.stream.exceptions.EmployeeStorageIsFullException;
import com.stream.stream.model.Employee;
import com.stream.stream.service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.util.Collection;


@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam int department,
                                @RequestParam int salary
    ) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        employeeService.addEmployee(firstName, lastName ,department,salary);
        return new Employee(firstName, lastName,department,salary);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName) throws EmployeeNotFoundException {
        employeeService.removeEmployee(firstName, lastName);
        return new Employee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName) throws EmployeeNotFoundException {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping("/getAll")

    public Collection<Employee> getAllEmployees() {

        return employeeService.getAllEmployees();
    }
}