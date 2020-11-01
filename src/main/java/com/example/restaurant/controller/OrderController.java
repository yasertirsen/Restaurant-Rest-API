package com.example.restaurant.controller;

import com.example.restaurant.model.Order;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private final MainService service;

    @GetMapping(value = "/all", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getAllOrders() {
        return service.getAllOrders();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getOrder(@PathVariable Long id) {
        return service.getOrder(id);
    }

    @PostMapping(value = "/add", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> addOrder(@Valid @RequestBody Order order, BindingResult result) {
        if(result.hasErrors())
            return new ResponseEntity<>("Check order information is correct", HttpStatus.BAD_REQUEST);
        return service.addOrder(order);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long id) {
        return service.deleteOrder(id);
    }

    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> updateOrder(@Valid @RequestBody Order order, BindingResult result) {
        if(result.hasErrors())
            return new ResponseEntity<>("Couldn't update user, check information is correct", HttpStatus.BAD_REQUEST);
        return service.updateOrder(order);
    }
}
