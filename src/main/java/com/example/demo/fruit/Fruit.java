package com.example.demo.fruit;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Fruit {
    @Id
    @SequenceGenerator(
            name = "fruit_sequence",
            sequenceName = "fruit_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "fruit_sequence"
    )
    private Long id;
    private String name;
    private String description;
    private LocalDate added;
    @Transient
    private Integer age;

    public Fruit() {
    }

    public Fruit(Long id, String name, String description, LocalDate added) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.added = added;
    }

    public Fruit(String name, String description, LocalDate added) {
        this.name = name;
        this.description = description;
        this.added = added;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getAdded() {
        return added;
    }

    public void setAdded(LocalDate added) {
        this.added = added;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", added=" + added +
                '}';
    }

    public Integer getAge() {
        return Period.between(added, LocalDate.now()).getYears();
    }
}
