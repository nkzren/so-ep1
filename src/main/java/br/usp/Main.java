package br.usp;

import br.usp.bcp.BCP;
import br.usp.escalonador.Escalonador;
import br.usp.utils.Metrics;
import br.usp.utils.ResourcesReader;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
            configureLogger(quantum);
            LOGGER.info("Quantum: " + quantum);


            Escalonador escalonador = geraEscalonador(quantum);

            escalonador.inicia();

            metrics.relatorio();
        } else {
            LOGGER.error("Quantum inexistente. Saindo");
        }
    }

    private void configureLogger(int quantum) {
        String logFile = "log" + formataQuantum(quantum) + ".txt";
        try {
            FileAppender appender = new FileAppender(new SimpleLayout(), logFile, false);
            // Adiciona os appenders no console e na saida de um arquivo
            BasicConfigurator.configure();
            BasicConfigurator.configure(appender);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String formataQuantum(int quantum) {
        String s = Integer.toString(quantum);
        if (s.length() == 1) {
            s = "0" + s;
        }
        return s;
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

        main.init();
    }
}
