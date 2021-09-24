package br.usp;

import br.usp.utils.ResourcesReader;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private final ResourcesReader reader = new ResourcesReader();

    public void init() {

        Optional<String> maybeQuantum = reader.readAll("quantum.txt");
        if (maybeQuantum.isPresent()) {
            int quantum = Integer.parseInt(maybeQuantum.get());
            System.out.println("Quantum: " + quantum);
        } else {
            LOGGER.error("Quantum inexistente");
        }

        System.out.println("Escolha um arquivo para ler");
        Scanner scanner = new Scanner(System.in);

        String test = scanner.next();

        List<String> list = reader.readLines(test);
        list.forEach(item -> {
            System.out.println("Lendo linha: " + item);
        });

    }

    public static void main(String[] args) {
        Main main = new Main();

        main.init();
    }
}
