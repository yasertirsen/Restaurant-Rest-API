package com.example.restaurant.service;

import com.example.restaurant.model.Menu;
import com.example.restaurant.model.MenuItem;
import com.example.restaurant.model.Order;
import com.example.restaurant.model.User;
import com.example.restaurant.repository.MenuItemRepository;
import com.example.restaurant.repository.MenuRepository;
import com.example.restaurant.repository.OrderRepository;
import com.example.restaurant.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class MainService {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final MenuItemRepository menuItemRepository;
    private final MenuRepository menuRepository;

    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepository.findAll();
        if(users.isEmpty())
            return new ResponseEntity<>("No users were found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    public ResponseEntity<?> getUser(Long id) {
        if(!userRepository.existsById(id))
            return new ResponseEntity<>("User with id " + id +" not found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userRepository.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<?> addUser(User user) {
        if(findByUsername(user.getUsername()) != null)
            return new ResponseEntity<>("Username already exits", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    private User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
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

    public ResponseEntity<?> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        if(orders.isEmpty())
            return new ResponseEntity<>("No orders were found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    public ResponseEntity<?> getOrder(Long id) {
        if(!orderRepository.existsById(id))
            return new ResponseEntity<>("Order with id " + id +" not found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(orderRepository.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<?> addOrder(Order order) {
        return new ResponseEntity<>(orderRepository.save(order), HttpStatus.CREATED);
    }

    public ResponseEntity<?> deleteOrder(Long id) {
        if(!orderRepository.existsById(id))
            return new ResponseEntity<>("Order with id " + id +" not found", HttpStatus.BAD_REQUEST);
        try {
            orderRepository.deleteById(id);
            return new ResponseEntity<>("Order with id " + id + " has been deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateOrder(Order order) {
        return new ResponseEntity<>(orderRepository.save(order), HttpStatus.OK);
    }

    public ResponseEntity<?> getAllMenuItems() {
        List<MenuItem> menuItems = menuItemRepository.findAll();
        if(menuItems.isEmpty())
            return new ResponseEntity<>("No Items were found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(menuItems, HttpStatus.OK);
    }

    public ResponseEntity<?> getMenuItem(Long id) {
        if(!menuItemRepository.existsById(id))
            return new ResponseEntity<>("Item with id " + id + " not found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(orderRepository.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<?> addMenuItem(MenuItem menuItem) {
        return new ResponseEntity<>(menuItemRepository.save(menuItem), HttpStatus.CREATED);
    }

    public ResponseEntity<?> deleteMenuItem(Long id) {
        if(!menuItemRepository.existsById(id))
            return new ResponseEntity<>("Item with id " + id +" not found", HttpStatus.BAD_REQUEST);
        try {
            menuItemRepository.deleteById(id);
            return new ResponseEntity<>("Item with id " + id + " has been deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateMenuItem(MenuItem menuItem) {
        return new ResponseEntity<>(menuItemRepository.save(menuItem), HttpStatus.OK);
    }

    public ResponseEntity<?> getAllMenus() {
        List<Menu> menus = menuRepository.findAll();
        if(menus.isEmpty())
            return new ResponseEntity<>("No menus were found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }


    public ResponseEntity<?> getMenu(Long id) {
        if(!menuRepository.existsById(id))
            return new ResponseEntity<>("Menu with id " + id + " not found", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(menuRepository.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<?> addMenu(Menu menu) {
        return new ResponseEntity<>(menuRepository.save(menu), HttpStatus.CREATED);
    }

    public ResponseEntity<?> deleteMenu(Long id) {
        if(!menuRepository.existsById(id))
            return new ResponseEntity<>("Menu with id " + id +" not found", HttpStatus.BAD_REQUEST);
        try {
            menuRepository.deleteById(id);
            return new ResponseEntity<>("Menu with id " + id + " has been deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateMenu(Menu menu) {
        return new ResponseEntity<>(menuRepository.save(menu), HttpStatus.OK);
    }
}
