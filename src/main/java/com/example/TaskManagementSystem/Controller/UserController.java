package com.example.TaskManagementSystem.Controller;

import com.example.TaskManagementSystem.Entity.Task;
import com.example.TaskManagementSystem.Entity.Users;
import com.example.TaskManagementSystem.Service.TaskService;
import com.example.TaskManagementSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    //GetMapping

    @GetMapping("/")
    public ResponseEntity<String> Login() {
        return userService.getLogin();
    }

    @GetMapping("/getTasks")
    public ResponseEntity<?> getTasks() {
         List<Task> isTaskPresent = taskService.getTasks();
            if (isTaskPresent.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No tasks found");
        }
            return ResponseEntity.ok(isTaskPresent);
    }

    @GetMapping("/viewOurDetails")
    public Users viewDetails() {
        return userService.viewDetails();
    }


    //PutMapping
    @PutMapping("/approveTask/{id}")
    public ResponseEntity<String> approveTask(@PathVariable Long id) {
        boolean approveTask = userService.approveTask(id);
        if (!approveTask) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot approve this task");
        }
        return ResponseEntity.ok("Task approved successfully");
    }

    @PutMapping("/completeTask/{id}")
    public ResponseEntity<String> completeTask(@PathVariable Long id) {
        boolean completeTask = userService.completeTask(id);
        if (!completeTask) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Cannot complete this task");
        }
        return ResponseEntity.ok("Task completed successfully");
    }
}
