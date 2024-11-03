package com.dictionaryapp.service;


import com.dictionaryapp.model.entity.User;
import com.dictionaryapp.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void userRegistered(User user){
        userRepository.save(user);
    }
}
