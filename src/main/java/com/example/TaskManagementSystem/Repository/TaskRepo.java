package com.example.TaskManagementSystem.Repository;

import com.example.TaskManagementSystem.Entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {

}
