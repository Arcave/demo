package com.example.demo.fruit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FruitRepository extends JpaRepository<Fruit, Long> {
    @Query("SELECT fruit FROM Fruit fruit WHERE fruit.name = ?1")
    Optional<Fruit> findFruitByName(String name);
}
