package org.example;
//объекты этого класса будет создавать Spring
public class TestBeanSomeClass {
    String name;

    public TestBeanSomeClass(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
