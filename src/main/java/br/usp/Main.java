package br.usp;

import br.usp.bcp.BCP;
import br.usp.escalonador.Escalonador;
import br.usp.utils.Metrics;
import br.usp.utils.ResourcesReader;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Classe principal que age como controladora do escalonador
 */
public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    private final ResourcesReader reader;

    private Metrics metrics;

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


            Escalonador escalonador = geraEscalonador(quantum);

            escalonador.inicia();

            metrics.relatorio();
        } else {
            LOGGER.error("Quantum inexistente. Saindo");
        }
    }

    /**
     * Inicializa todos os processos carregados inicialmente
     * @param quantum
     */
    public Escalonador geraEscalonador(int quantum) {

        // Le os arquivos na pasta de programas e inicializa o contador de metricas
        List<File> files = reader.getFilesInFolder("programas");
        this.metrics = new Metrics(files.size(), quantum);

        Escalonador escalonador = new Escalonador(metrics, quantum);
        files.forEach(file -> {
            List<String> lines = reader.readLines(file);
            escalonador.carregaBCP(BCP.of(lines));
        });
        return escalonador;
    }

    public static void main(String[] args) {
        Main main = new Main(new ResourcesReader());

        // Configura o log4j
        BasicConfigurator.configure();

        main.init();
    }
}
