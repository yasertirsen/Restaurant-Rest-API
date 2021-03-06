package com.example.restaurant.controller;

import com.example.restaurant.model.Address;
import com.example.restaurant.model.Order;
import com.example.restaurant.model.User;
import com.example.restaurant.service.MainService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final MainService service;

    @GetMapping(value = "/all", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getAllUsers() {
        return service.getAllUsers();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        return service.getUser(id);
    }

    @PostMapping(value = "/register", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result) {
        if(result.hasErrors())
            return new ResponseEntity<>("Check user information is correct", HttpStatus.BAD_REQUEST);
        return service.addUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return service.deleteUser(id);
    }

    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> updateUser(@Valid @RequestBody User user, BindingResult result) {
        if(result.hasErrors())
            return new ResponseEntity<>("Couldn't update user, check information is correct", HttpStatus.BAD_REQUEST);
        return service.updateUser(user);
    }

    @PutMapping(value = "/{userId}/updateaddress", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> updateUserAddress(@PathVariable Long userId, @Valid @RequestBody Address address, BindingResult result) {
        if(result.hasErrors())
            return new ResponseEntity<>("Couldn't update address, check information is correct", HttpStatus.BAD_REQUEST);
        return service.updateAddress(address, userId);
    }

    @PostMapping(value = "/{userId}/addorder", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> addOrder(@PathVariable Long userId, @Valid @RequestBody Order order, BindingResult result) {
        if(result.hasErrors())
            return new ResponseEntity<>("Check order information is correct", HttpStatus.BAD_REQUEST);
        return service.addOrder(order, userId);
    }

    @GetMapping(value = "/{userId}/allorders", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> allOrders(@PathVariable Long userId) {
        return service.getUserOrders(userId);
    }

}
