package br.usp;

import br.usp.utils.ResourcesReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    ResourcesReader reader = new ResourcesReader();

    Main subject = new Main(reader);

    @Test
    void should_return_hello_world() {

        // FIXME: dumb test
        assertTrue(true);
    }
}