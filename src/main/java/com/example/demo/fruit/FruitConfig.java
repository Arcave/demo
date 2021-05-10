package com.example.demo.fruit;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;


@Configuration
public class FruitConfig {
    @Bean
    CommandLineRunner commandLineRunner(FruitRepository repository) {
        return args -> {
            Fruit mango  = new Fruit("Mango", "An orange fruit", LocalDate.of(2000, Month.JANUARY, 5));
            Fruit apple  = new Fruit("Apple", "An green fruit", LocalDate.of(2000, Month.JANUARY, 5));
            repository.saveAll(
                    List.of(mango, apple)
            );
        };
    }
}
