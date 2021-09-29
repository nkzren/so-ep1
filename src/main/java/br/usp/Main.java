package br.usp;

import br.usp.escalonador.Escalonador;
import br.usp.processo.Processo;
import br.usp.utils.ResourcesReader;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Classe principal que age como controladora do escalonador
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private final ResourcesReader reader = new ResourcesReader();

    /**
     * Inicializa o escalonador a partir do arquivo de quantum
     */
    public void init() {

        Optional<String> maybeQuantum = reader.readAll("quantum.txt");
        if (maybeQuantum.isPresent()) {
            int quantum = Integer.parseInt(maybeQuantum.get());
            System.out.println("Quantum: " + quantum);

            Escalonador escalonador = new Escalonador(quantum);

            carregaProcessos(escalonador);
        } else {
            LOGGER.error("Quantum inexistente. Saindo");
        }
    }

    /**
     * Inicializa todos os processos carregados inicialmente
     */
    public void carregaProcessos(Escalonador escalonador) {
        Stream<File> files = reader.getFilesInFolder("programas");

        files.forEach(file -> {
            List<String> lines = reader.readLines(file);
        });
    }

    public static void main(String[] args) {
        Main main = new Main();

        main.init();
    }
}
