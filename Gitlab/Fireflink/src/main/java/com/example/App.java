package com.example;

public class App {
    public String sayHello() {
        return "Hello, World!";
    }

    public static void main(String[] args) {
        App app = new App();
        System.out.println(app.sayHello());
    }
}
