package com.example.TaskManagementSystem.Repository;

import com.example.TaskManagementSystem.Entity.Assign;
import com.example.TaskManagementSystem.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepo extends JpaRepository<Users, Long> {
    Users findByUserName(String userName);
    void deleteByRole(String role);
    long countByRole(String role);
    List<Users> findByAssign(Assign assign);
}
