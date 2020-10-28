package com.example.restaurant.service;

import com.example.restaurant.model.User;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MainService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty())
            return new ResponseEntity<>("No users were found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> getUser(Long id) {
        if(!userRepository.existsById(id))
            return new ResponseEntity<>("User with id " + id +" not found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<?> addUser(User user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    public ResponseEntity<?> deleteUser(Long id) {
        if(!userRepository.existsById(id))
            return new ResponseEntity<>("User with id " + id +" not found", HttpStatus.BAD_REQUEST);
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>("User with id " + id + " has been deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateUser(User user) {
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    }
}
