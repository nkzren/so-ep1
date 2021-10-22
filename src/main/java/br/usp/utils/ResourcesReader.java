package br.usp.utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ResourcesReader {

    private static final Logger LOGGER = Logger.getLogger(ResourcesReader.class);

    private static final String BASE_PATH = "src/main/resources/";

    public Optional<String> readAll(String fileName) {
        try {
            return Optional.of(Files.readString(Paths.get(BASE_PATH + fileName)).replaceAll("\n", ""));
        } catch (IOException e) {
            LOGGER.error("Erro na leitura do arquivo: " + fileName, e);
            return Optional.empty();
        }
    }

    /**
     * Funcao para ler as linhas de um arquivo
     * @param file O arquivo
     * @return Uma lista com as linhas de um arquivo
     */
    public List<String> readLines(File file) {
        try {
            Stream<String> lines = Files.lines(file.toPath());
            return lines.collect(Collectors.toList());
        } catch (IOException e) {
            LOGGER.error("Erro na leitura do arquivo: " + file.getName(), e);
            return List.of();
        }
    }

    /**
     * Funcao para ler todos os arquivos de um diretorio
     * @param folderName o nome da pasta
     * @return a stream de arquivos, ordenada por ordem alfabetica pelo nome do arquivos
     */
    public List<File> getFilesInFolder(String folderName) {
        try {
            Stream<Path> filePaths = Files.walk(Paths.get(BASE_PATH + folderName));

            return filePaths
                    .map(Path::toFile)
                    .filter(File::isFile)
                    .sorted((file, other) -> file.getName().compareToIgnoreCase(other.getName()))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            LOGGER.error("Erro na leitura da pasta: " + folderName, e);
            return List.of();
        }
    }
}
