package br.usp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public String hello() {
        return "Hello World";
    }

    public static void main(String[] args) throws IOException {
        System.out.println(new Main().hello());

        System.out.println("Escolha um arquivo para ler");
        Scanner scanner = new Scanner(System.in);

        String test = scanner.next();
        Stream<String> lines = Files.lines(Paths.get("src/main/resources/programas/" + test), StandardCharsets.UTF_8);

        lines.forEach(line -> {
            System.out.println("Lendo linha: " + line);
        });
    }
}
