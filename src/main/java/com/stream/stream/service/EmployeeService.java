package com.stream.stream.service;

import com.stream.stream.exceptions.EmployeeAlreadyAddedException;
import com.stream.stream.exceptions.EmployeeNotFoundException;
import com.stream.stream.exceptions.EmployeeStorageIsFullException;
import com.stream.stream.model.Employee;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class EmployeeService {
    private final Map<String, Employee> employees;
    private static final int MAX_EMPLOYEES = 10;

    public EmployeeService() {
        employees = new HashMap<>();
    }

    public Employee addEmployee(String firstName, String lastName) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            return employees.remove(employee.getFullName());



        }

        throw new EmployeeNotFoundException();
    }

    public Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException {

        Employee employee = new Employee(firstName, lastName);
        if (employees.containsKey(employee.getFullName())) {
            return employees.get(employee.getFullName());

        }

        throw new EmployeeNotFoundException();
    }


    public Collection<Employee> getAllEmployees() {
        return Collections.unmodifiableCollection(employees.values());

    }
}