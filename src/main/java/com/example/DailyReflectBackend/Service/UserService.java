package com.example.DailyReflectBackend.Service;

import com.example.DailyReflectBackend.Model.User;
import com.example.DailyReflectBackend.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    public User getUserById(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        if(userOptional.isEmpty()) {
            // TODO: Implement exceptions
        }

        return userOptional.get();
    }
}
