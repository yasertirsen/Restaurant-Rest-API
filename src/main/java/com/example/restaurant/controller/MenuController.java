package com.example.restaurant.controller;

import com.example.restaurant.model.Menu;
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
@RequestMapping("/api/menus")
@AllArgsConstructor
public class MenuController {

    private final MainService service;

    @GetMapping(value = "/all", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getAllMenus() {
        return service.getAllMenus();
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getMenu(@PathVariable Long id) {
        return service.getMenu(id);
    }

    @PostMapping(value = "/add", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> addMenu(@Valid @RequestBody Menu menu, BindingResult result) {
        if(result.hasErrors())
            return new ResponseEntity<>("Check order information is correct", HttpStatus.BAD_REQUEST);
        return service.addMenu(menu);
    }

    @PostMapping(value = "{menuId}/additem", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> addMenuItem(@PathVariable Long menuId, @Valid @RequestBody MenuItem menuItem, BindingResult result) {
        if(result.hasErrors())
            return new ResponseEntity<>("Check item information is correct", HttpStatus.BAD_REQUEST);
        return service.addMenuItem(menuItem, menuId);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMenu(@PathVariable Long id) {
        return service.deleteMenu(id);
    }

    @PutMapping(value = "/update", produces = "application/json", consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> updateMenu(@Valid @RequestBody Menu menu, BindingResult result) {
        if(result.hasErrors())
            return new ResponseEntity<>("Couldn't update user, check information is correct", HttpStatus.BAD_REQUEST);
        return service.updateMenu(menu);
    }


}
