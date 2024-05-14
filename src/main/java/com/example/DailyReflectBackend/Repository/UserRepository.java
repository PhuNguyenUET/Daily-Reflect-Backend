package com.example.DailyReflectBackend.Repository;

import com.example.DailyReflectBackend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
