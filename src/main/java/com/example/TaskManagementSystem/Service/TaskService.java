package com.example.TaskManagementSystem.Service;

import com.example.TaskManagementSystem.Entity.Task;
import com.example.TaskManagementSystem.Repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepo taskRepo;

    public List<Task> getTasks() {
        return taskRepo.findAll();
    }

    public void addTask(Task task1) {
        taskRepo.save(task1);
    }

    public boolean updateTask(Task task2) {
        Optional<Task> task = taskRepo.findById(task2.getId());
        if (task.isEmpty()) {
            return false;
        }
        taskRepo.save(task2);
        return true;
    }

    public boolean deleteTask(Long id) {
        Optional<Task> taskId = taskRepo.findById(id);
        if (taskId.isEmpty()) {
            return false;
        }
        taskRepo.deleteById(id);
        return true;
    }

    public boolean deleteAllTask() {
        List<Task> taskPresent = taskRepo.findAll();
        if (taskPresent.isEmpty()) {
            return false;
        }
        taskRepo.deleteAll();
        return true;
    }
}
