package br.usp.escalonador;

import br.usp.bcp.BCP;
import br.usp.processo.Estado;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import static br.usp.processo.Estado.*;

public class Escalonador {

    private static final Logger LOGGER = Logger.getLogger(Escalonador.class.getSimpleName());

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

            LOGGER.info("EXECUTANDO PROCESSO: " + bcp.getNomePrograma());
            Estado estado = null;

            this.bloqueados.forEach(BCP::decrementaQuantum);

            for(int i = 0; i < quantum; i++){
                estado = bcp.executaProxInstrucao();

                if(FINALIZADO.equals(estado)){
                    tabelaProcessos.removeProcesso(bcp.getRef());
                    break;
                } else if(BLOQUEADO.equals(estado)){
                    bloqueados.offer(bcp);
                    break;
                }
            }

            if(EXECUTANDO.equals(estado)){
                prontos.offer(bcp);
                bcp.trocaEstado(PRONTO);
            }

            if(!this.bloqueados.isEmpty()){
                BCP bcpBloqueado = this.bloqueados.peek();

                if(bcpBloqueado.verificaPronto()){

                    this.prontos.offer(this.bloqueados.poll());
                }
            }


        }
    }

}
