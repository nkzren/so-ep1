package br.usp;

import br.usp.bcp.BCP;
import br.usp.escalonador.Escalonador;
import br.usp.utils.ResourcesReader;
import org.apache.log4j.BasicConfigurator;
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

    private final ResourcesReader reader;

    public Main(ResourcesReader reader) {
        this.reader = reader;
    }

    /**
     * Inicializa o escalonador a partir do arquivo de quantum
    */
    public void init() {

        Optional<String> maybeQuantum = reader.readAll("quantum.txt");
        if (maybeQuantum.isPresent()) {
            int quantum = Integer.parseInt(maybeQuantum.get());
            LOGGER.info("Quantum: " + quantum);

            Escalonador escalonador = new Escalonador(quantum);

            carregaProcessos(escalonador);

            escalonador.inicia();
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
            escalonador.carregaBCP(BCP.of(lines));
        });
    }

    public static void main(String[] args) {
        Main main = new Main(new ResourcesReader());

        // Configura o log4j
        BasicConfigurator.configure();

        main.init();
    }
}
