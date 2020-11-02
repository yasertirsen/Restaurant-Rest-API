package com.example.restaurant.controller;

import com.example.restaurant.model.Address;
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
@RequestMapping("/api/addresses")
@AllArgsConstructor
public class AddressController {

    private final MainService service;

    @GetMapping(value = "/all", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getAllAddresses() {
        return service.getAllMenuAddresses();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getAddress(@PathVariable Long id) {
        return service.getAddress(id);
    }

    @PostMapping(value = "/add", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> addAddress(@Valid @RequestBody Address address, BindingResult result) {
        if(result.hasErrors())
            return new ResponseEntity<>("Check address information is correct", HttpStatus.BAD_REQUEST);
        return service.addAddress(address);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable Long id) {
        return service.deleteAddress(id);
    }

    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> updateMenuItem(@Valid @RequestBody Address address, BindingResult result) {
        if(result.hasErrors())
            return new ResponseEntity<>("Couldn't update address, check information is correct", HttpStatus.BAD_REQUEST);
        return service.updateAddress(address);
    }
}
