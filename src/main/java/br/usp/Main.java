package br.usp;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public void init() {
        try {
            int quantum = Integer.parseInt(Files.readString(Paths.get(BASE_PATH + "quantum.txt")).replaceAll("\n", ""));
            System.out.println("Quantum: " + quantum);
        } catch (IOException e) {
            LOGGER.error("Erro na leitura do quantum: ", e);
        }

        System.out.println("Escolha um arquivo para ler");
        Scanner scanner = new Scanner(System.in);

        String test = scanner.next();
        try {
            Stream<String> lines = Files.lines(Paths.get("src/main/resources/programas/" + test), StandardCharsets.UTF_8);

            lines.forEach(line -> {
                System.out.println("Lendo linha: " + line);
            });
        } catch (IOException e) {
            LOGGER.error("Erro na leitura do arquivo selecionado " + test + ": ", e);
        }
    }

    private static final String BASE_PATH = "src/main/resources/programas/";

    public static void main(String[] args) {
        Main main = new Main();

        main.init();
    }
}
