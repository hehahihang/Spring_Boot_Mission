package com.company;

public class Lecturer extends AbstractPerson implements Person{
    @Override
    public void speak() {
        name = "강사";
        age = 45;
        super.speak();
    }
}
