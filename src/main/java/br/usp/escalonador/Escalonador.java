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
        while(tabelaProcessos.temProcesso()) {
            this.bloqueados.forEach(BCP::decrementaQuantum);
            if (!prontos.isEmpty()) {
                escalonaProcesso();
            } else {
                LOGGER.warn("FILA DE PRONTOS VAZIA");
            }

            // Verifica a lista de processos bloqueados
            if(!this.bloqueados.isEmpty()){
                BCP bcpBloqueado = this.bloqueados.peek();

                if(bcpBloqueado != null && bcpBloqueado.verificaPronto()){
                    BCP pronto = this.bloqueados.poll();
                    LOGGER.info("REENFILEIRANDO PARA PRONTO: " + pronto.getNomePrograma());
                    this.prontos.offer(pronto);
                }
            }
        }
    }

    private void escalonaProcesso() {
        BCP bcp = prontos.poll();

        Estado estado = null;

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
    }
}
