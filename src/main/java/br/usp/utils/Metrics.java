package br.usp.utils;

import org.apache.log4j.Logger;

public class Metrics {
    private static final Logger LOGGER = Logger.getLogger(Metrics.class.getName());

    private final int qtdProcessos;
    private final int quantum;

    private int trocasProcesso;

    private int qtdInstrucoes;
    private int quantaUtilizado;


    public Metrics(int qtdProcessos, int quantum) {
        this.qtdProcessos = qtdProcessos;
        this.quantum = quantum;
        this.trocasProcesso = 0;
        this.quantaUtilizado = 0;
    }

    public void incrementaTrocasProcesso() {
        trocasProcesso++;
    }

    public void instrucoesPorQuantum(int instrucoes) {
        this.qtdInstrucoes += instrucoes;
        this.quantaUtilizado++;
    }

    public void relatorio() {
        System.out.println();
        System.out.println();
        LOGGER.info("##### INICIANDO RELATORIO DE METRICAS #####");
        LOGGER.info("QUANTUM: \t\t" + quantum);
        LOGGER.info("MEDIA DE TROCAS: \t" + (double) trocasProcesso / qtdProcessos);
        LOGGER.info("MEDIA DE INSTRUCOES: \t" + (double) qtdInstrucoes / quantaUtilizado);
    }
}
