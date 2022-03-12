package com.company;

public class Student extends AbstractPerson implements Person{

    @Override
    public void speak() {
        name = "학생";
        age = 18;
        super.speak();
    }
}
