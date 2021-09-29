package br.usp.escalonador;

import br.usp.bcp.BCP;
import br.usp.processo.Processo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Escalonador {

    private final Integer quantum;

    private final Queue<Processo> prontos = new LinkedBlockingQueue<>();

    private final Queue<Processo> bloqueados = new LinkedList<>();

    private final TabelaProcessos tabelaProcessos = new TabelaProcessos();

    public Escalonador(Integer quantum) {
        this.quantum = quantum;
    }

    public void carregaBCP(BCP blocoComandos) {
        tabelaProcessos.adicionaProcesso(blocoComandos);
        prontos.offer(blocoComandos.getProcesso());
    }
}
