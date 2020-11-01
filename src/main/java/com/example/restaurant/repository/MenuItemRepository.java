package com.example.restaurant.repository;

import com.example.restaurant.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    Optional<MenuItem> findItemByName(String name);
}
