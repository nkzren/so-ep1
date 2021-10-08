package br.usp.escalonador;

import br.usp.bcp.BCP;
import br.usp.processo.Estado;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static br.usp.processo.Estado.FINALIZADO;

public class Escalonador {

    private final Integer quantum;

    private final Queue<BCP> prontos = new LinkedBlockingQueue<>();

    private final Queue<BCP> bloqueados = new LinkedList<>();

    private final TabelaProcessos tabelaProcessos = new TabelaProcessos();

    public Escalonador(Integer quantum) {
        this.quantum = quantum;
    }

    public void carregaBCP(BCP blocoComandos) {
        tabelaProcessos.adicionaProcesso(blocoComandos);
        prontos.offer(blocoComandos);
    }

    public void inicia(){
        while(!prontos.isEmpty()){
            BCP bcp = prontos.poll();

            for(int i = 0; i < quantum; i++){
                Estado estado = bcp.executaProxInstrucao();

                if(estado.equals(FINALIZADO)){
                    return;
                }
            }
        }
    }
}
