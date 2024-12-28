package com.pms.service;

import com.pms.entity.User;
import com.pms.exception.ResourceNotFoundException;
import com.pms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;


    @Cacheable(value = "users")
    public User getUserByUsername(String username) {
        try {
            return repository.findByUsername(username)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @CachePut(value = "users", key = "#user.username")
    public User saveUser(User user) {
        return repository.save(user);
    }
}
