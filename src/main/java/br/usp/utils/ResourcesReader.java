package br.usp.utils;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResourcesReader {

    private static final Logger LOGGER = Logger.getLogger(ResourcesReader.class);

    private static final String BASE_PATH = "src/main/resources/programas/";

    public Optional<String> readAll(String fileName) {
        try {
            return Optional.of(Files.readString(Paths.get(BASE_PATH + fileName)).replaceAll("\n", ""));
        } catch (IOException e) {
            LOGGER.error("Erro na leitura do arquivo: " + fileName, e);
            return Optional.empty();
        }
    }

    public List<String> readLines(String fileName) {
        try {
            Stream<String> lines = Files.lines(Paths.get("src/main/resources/programas/" + fileName), StandardCharsets.UTF_8);

            return lines.collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Erro na leitura do arquivo: " + fileName, e);
            return List.of();
        }
    }
}
