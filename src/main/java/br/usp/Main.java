package br.usp;

public class Main {

    public String hello() {
        return "Hello World";
    }

    public static void main(String[] args) {
        System.out.println(new Main().hello());
    }
}
