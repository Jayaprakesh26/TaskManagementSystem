package com.example.TaskManagementSystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String Title;
    private String Description;
    private LocalDate start_date;
    private LocalDate due_date;
    private LocalDate completed_date;
    private String Assigned_to;

    @Enumerated(EnumType.STRING)
    private Status status;


}
