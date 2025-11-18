package com.example.TaskManagementSystem.Controller;

import com.example.TaskManagementSystem.Entity.AssignRequest;
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
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    TaskService taskService;

    //Get Mapping
    @GetMapping("/")
    public ResponseEntity<String> Login() {
        return userService.getLogin();
    }

    @GetMapping("/getUsers")
    public List<Users> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/getTasks")
    public ResponseEntity<?> getTasks() {
        List<Task> isTaskPresent = taskService.getTasks();
        if (isTaskPresent.isEmpty()) {
            return ResponseEntity.ok("No tasks found");
        }
    return ResponseEntity.ok(isTaskPresent);
    }

    @GetMapping("/getNotAssigned")
    public ResponseEntity<?> getNotAssigned() {
        List<Users> notAssign = userService.getNotAssigned();
        if (notAssign.isEmpty()) {
            return ResponseEntity.ok("All users have been assigned");
        }
        return ResponseEntity.ok(notAssign);
    }


    //PostMapping
    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody Users users) {
        userService.addUser(users);
        return ResponseEntity.ok(users.getUserName()+" details added successfully");
    }

    @PostMapping("/addTask")
    public ResponseEntity<String> addTask(@RequestBody Task task1) {
        taskService.addTask(task1);
        return ResponseEntity.ok(task1.getTitle()+" task added successfully");
    }

    //PutMapping
    @PutMapping("/updateUser")
    public ResponseEntity<String> updateUser(@RequestBody Users users1) {
        boolean updated = userService.updateUser(users1);
        if (!updated) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User details entered Not found");
        }
        return ResponseEntity.ok("ID: "+users1.getId()+", Name: "+users1.getUserName()+" details updated");
    }

    @PutMapping("/updateTaskAssignRequest")
    public ResponseEntity<String> updateTask(@RequestBody Task task2) {
        boolean taskPresent = taskService.updateTask(task2);
        if (!taskPresent) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task entered Not found");
        }
        return ResponseEntity.ok(task2.getTitle()+" Task details have been updated successfully");
    }

    @PutMapping("/assignTask")
    public ResponseEntity<String> assignTask(@RequestBody AssignRequest request) {
        boolean task = userService.assignTask(request.getTaskId(),request.getUserId());
        if (!task) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or Task not found");
        }
        return ResponseEntity.ok("Task assigned successfully");
    }


    //DeleteMapping
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        boolean UserIdPresent = userService.deleteUser(id);
        if (!UserIdPresent) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User ID and details entered Not found!");
        }
        return ResponseEntity.ok("User "+id+" Details have been deleted successfully");
    }

    @DeleteMapping("/deleteTask/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id) {
        boolean taskIdPresent = taskService.deleteTask(id);
        if (!taskIdPresent) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task ID and details entered Not found!");
        }
        return ResponseEntity.ok("Task "+id+" Details have been deleted successfully");
    }

    @DeleteMapping("/deleteAllUser")
    public ResponseEntity<String> deleteAllUser() {
        boolean userPresent = userService.deleteAllUser();
        if (!userPresent) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No users were found to delete");
        }
        return ResponseEntity.ok("All non-admin users have been deleted");
    }

    @DeleteMapping("/deleteAllTask")
    public ResponseEntity<String> deleteAllTask() {
        boolean taskPresent = taskService.deleteAllTask();
        if (!taskPresent) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No tasks were found to delete");
        }
        return ResponseEntity.ok("All tasks have been deleted successfully");
    }

}
