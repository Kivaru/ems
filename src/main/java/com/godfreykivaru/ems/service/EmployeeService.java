package com.godfreykivaru.ems.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.godfreykivaru.ems.entity.Employee;
import com.godfreykivaru.ems.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Page<Employee> getAllEmployees(Pageable pageable){
        return employeeRepository.findAll(pageable);
    }

    public Employee getEmployeeById(Long id){
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee updateEmployee(Long id, Employee employee){

        Employee existingEmployee = getEmployeeById(id);

        if(existingEmployee != null){
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setDepartment(employee.getDepartment());
            existingEmployee.setSalary(employee.getSalary());
            return employeeRepository.save(existingEmployee);
        }

        return null;

    }

    public void deleteEmployee(Long id){
        employeeRepository.deleteById(id);
    }

}
