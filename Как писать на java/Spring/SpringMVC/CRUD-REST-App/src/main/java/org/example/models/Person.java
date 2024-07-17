package org.example.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Person {
    @Min(0)
    private int id;
    @NotEmpty(message = "name not empty")
    @Size(min = 2, max = 30, message = "size 2-30")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "the name must consist of letters of the Latin alphabet")
    private String name;

    public Person(){}

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
