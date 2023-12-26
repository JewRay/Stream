package com.stream.stream.service;

import com.stream.stream.exceptions.EmployeeAlreadyAddedException;
import com.stream.stream.exceptions.EmployeeNotFoundException;
import com.stream.stream.exceptions.EmployeeStorageIsFullException;
import com.stream.stream.model.Employee;

import java.util.*;
import java.util.stream.Stream;

public class EmployeeService {
    private final Map<String, Employee> employees;
    private static final int MAX_EMPLOYEES = 10;

    public EmployeeService() {
        employees = new HashMap<>();
    }

    public Employee addEmployee(String firstName, String lastName, int department, int salary) throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        Employee employee = new Employee(firstName, lastName, department, salary);
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

    private String buildKey(String firstname, String lastName) {
        return firstname + lastName;
    }

    public void changeDepartment(Employee employee, int newDepartment) {
        String key = buildKey(employee.getFirstName(), employee.getLastName());
        if (employees.containsKey(key)) {
            Employee emp = employees.get(key);
            emp.setDepartment(newDepartment);
        }
    }

    public void printEmployeesByDepartment() {
        Stream.iterate(1, department -> department + 1)
                .limit(5)
                .forEach(department -> {
                    System.out.println("Сотрудникни из отдела " + department + ":");
                    employees.values().stream()
                            .filter((employee -> employee.getDepartment() == department))
                            .forEach(employee -> System.out.println(employee.getFullName()));
                });
    }

    public void indexSalaryForDepartment(int department) {
        employees.values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .average()
                .orElse(0);
    }





    public double totalSalaryForDepartment(int department) {
        return employees.values().stream()
                .filter(employee -> employee.getDepartment() == department)
                .mapToInt(Employee::getSalary)
                .sum();
    }


}