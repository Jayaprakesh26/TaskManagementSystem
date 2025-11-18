package com.example.TaskManagementSystem.Service;

import com.example.TaskManagementSystem.Controller.UserPrincipal;
import com.example.TaskManagementSystem.Entity.Assign;
import com.example.TaskManagementSystem.Entity.Status;
import com.example.TaskManagementSystem.Entity.Task;
import com.example.TaskManagementSystem.Entity.Users;
import com.example.TaskManagementSystem.Repository.TaskRepo;
import com.example.TaskManagementSystem.Repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    TaskRepo taskRepo;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = userRepo.findByUserName(username);
        if (users==null) {
            throw new UsernameNotFoundException("Username entered not found");
        }
        else {
            return new UserPrincipal(users);
        }
    }

    public ResponseEntity<String> getLogin() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return ResponseEntity.ok("Welcome to Task Management system "+ username +"!");
    }

    public List<Users> getUsers() {
        return userRepo.findAll();
    }

    public void addUser(Users users) {
        users.setPassword(encoder.encode(users.getPassword()));
        userRepo.save(users);
    }

    public boolean updateUser(Users users1) {
        Optional<Users> users = userRepo.findById(users1.getId());
        if (users.isEmpty()) {
            return false;
        }
        users1.setPassword(encoder.encode(users1.getPassword()));
        userRepo.save(users1);
        return true;
    }

    public boolean deleteUser(Long id) {
        Optional<Users> usersId = userRepo.findById(id);
        if (usersId.isEmpty()) {
            return false;
        }
        userRepo.deleteById(id);
        return true;
    }

    @Transactional
    public boolean deleteAllUser() {
        long count = userRepo.countByRole("USER");
        if (count==0) {
            return false;
        }
        userRepo.deleteByRole("USER");
        return true;
    }

    public List<Users> getNotAssigned() {
        return userRepo.findByAssign(Assign.TASK_NOT_ASSIGNED);
    }

    public boolean assignTask(long taskId, long userId) {
        Optional<Task> getTasks = taskRepo.findById(taskId);
        Optional<Users> getUsers = userRepo.findById(userId);

        if (getTasks.isEmpty()) {
            return false;
        }
        if (getUsers.isEmpty()) {
            return false;
        }

        Task task = getTasks.get();
        Users users = getUsers.get();

        if (task.getAssigned_to()!=null) {
            return false;
        }

        task.setAssigned_to(users.getUserName());
        users.setAssign(Assign.TASK_ASSIGNED);
        taskRepo.save(task);
        userRepo.save(users);
        return true;
    }

    public Users viewDetails() {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Users users = userRepo.findByUserName(userName);
        return users;
    }

    public boolean approveTask(Long id) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Task task = taskRepo.findById(id).orElse(null);

        if (task==null) {
            return false;
        }
        if (!userName.equals(task.getAssigned_to())) {
            return false;
        }

        if (task.getStatus().equals(Status.PENDING)) {
            task.setStatus(Status.IN_PROGRESS);
            taskRepo.save(task);
            return true;
        }
        return false;
    }

    public boolean completeTask(Long id) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        Task task = taskRepo.findById(id).orElse(null);

        if (task==null) {
            return false;
        }
        if (!userName.equals(task.getAssigned_to())) {
            return false;
        }

        if (task.getStatus().equals(Status.IN_PROGRESS)) {
            task.setStatus(Status.COMPLETED);
            taskRepo.save(task);
            return true;
        }
        return false;
    }
}