package br.usp;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    Main subject = new Main();

    @Test
    void should_return_hello_world() {
        String result = subject.hello();

        assertEquals(result, "Hello World");
    }
}