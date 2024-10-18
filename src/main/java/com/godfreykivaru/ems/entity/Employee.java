package com.godfreykivaru.ems.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@NoArgsConstructor

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First Name Field is required")
    private String firstName;

    @NotBlank(message = "Last Name Field is required")
    private String lastName;

    @NotBlank(message = "Email Field is required")
    @Email(message = "Invalid Email! Please check it.")
    private String email;

    @NotBlank(message = "Department Field is required")
    private String department;

    @NotNull(message = "Salary Field is required")
    @Min(value = 0, message = "Salary must start with at least 0")
    private Double salary;

    public Employee(Long id, String firstName, String lastName, String email, String department, Double salary){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.department = department;
        this.salary = salary;

    }

    //@Data Lombok annotation used to generate getters, setters etc.


}
