package com.company;

//상속 클래스는 부모에서 선언/정의를 모두하여 자식은 메소드 변수를 그대로 이용할 수 있다.
public abstract class AbstractPerson implements Person{
    protected String name;
    protected int age;

    @Override
    public void speak() {
       System.out.println("제 이름은 " + this.name + "이고 나이는 " + this.age +"살 입니다.");
    }
}
