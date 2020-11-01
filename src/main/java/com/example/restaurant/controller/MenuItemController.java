package com.example.restaurant.controller;

import com.example.restaurant.model.MenuItem;
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
@RequestMapping("/api/items")
@AllArgsConstructor
public class MenuItemController {

    private final MainService service;

    @GetMapping(value = "/all", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getAllItems() {
        return service.getAllMenuItems();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getMenuItem(@PathVariable Long id) {
        return service.getMenuItem(id);
    }

    @PostMapping(value = "/add", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> addMenuItem(@Valid @RequestBody MenuItem menuItem, BindingResult result) {
        if(result.hasErrors())
            return new ResponseEntity<>("Check item information is correct", HttpStatus.BAD_REQUEST);
        return service.addMenuItem(menuItem);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMenuItem(@PathVariable Long id) {
        return service.deleteMenuItem(id);
    }

    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> updateMenuItem(@Valid @RequestBody MenuItem menuItem, BindingResult result) {
        if(result.hasErrors())
            return new ResponseEntity<>("Couldn't update user, check information is correct", HttpStatus.BAD_REQUEST);
        return service.updateMenuItem(menuItem);
    }
}
