package com.example.demo.fruit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/fruit")
public class FruitController {
    private final FruitService fruitService;

    @Autowired
    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @GetMapping
    public List<Fruit> getFruit() {
        return fruitService.getFruits();
    }

    @PostMapping
    public void registerNewFruit(@RequestBody Fruit fruit) {
        fruitService.addNewFruit(fruit);
    }

    @DeleteMapping(path = "{fruitId}")
    public void deleteStudent(@PathVariable("fruitId") Long fruitId) {
        fruitService.deleteStudent(fruitId);
    }

    @PutMapping(path = "{fruitId}")
    public void updateFruit(
        @PathVariable("fruitId") Long fruitId,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String description
    ) {
        fruitService.updateStudent(fruitId, name, description);
    }
}
