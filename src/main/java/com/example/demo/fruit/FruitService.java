package com.example.demo.fruit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FruitService {
    private final FruitRepository fruitRepository;

    @Autowired
    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public List<Fruit> getFruits() {
        return fruitRepository.findAll();
    }

    public void addNewFruit(Fruit fruit) {
        Optional<Fruit> fruitByName = fruitRepository.findFruitByName(fruit.getName());
        if (fruitByName.isPresent()) {
            throw new IllegalStateException("name already exists");
        }
        fruitRepository.save(fruit);
    }

    public void deleteStudent(Long fruitId) {
        var exists = fruitRepository.existsById(fruitId);
        if(!exists){
            throw new IllegalStateException("fruit with id " + fruitId + " does not exist");
        }
        fruitRepository.deleteById(fruitId);
    }

    @Transactional
    public void updateStudent(Long fruitId, String name, String description) {
        var fruit = fruitRepository.findById(fruitId)
                .orElseThrow(() -> new IllegalStateException("fruit with id " + fruitId + " does not exist"));
        if(name != null && name.length() > 0 && !Objects.equals(fruit.getName(), name)){
            fruit.setName(name);
        }
        if(description != null && description.length() > 0 && !Objects.equals(fruit.getDescription(), description)){
            fruit.setDescription(description);
        }
    }
}
