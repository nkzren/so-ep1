package br.usp.bcp;

import br.usp.processo.Estado;
import br.usp.processo.Processo;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.UUID;

public class BCP {

    private static final Logger LOGGER = Logger.getLogger(BCP.class.getSimpleName());

    private final String nomePrograma;

    private int contadorPrograma;

    private final Processo processo;

    private final UUID ref;


    /**
     * Funcao contendo toda a inicializacao do BCP a partir das linhas do arquivo de texto lido
     * @param instrucoes lista de instrucoes do arquivo
     * @return BCP inicializado
     */
    public static BCP of(List<String> instrucoes) {
        String nome = instrucoes.remove(0);
        return new BCP(nome, new Processo(instrucoes));
    }

    private BCP(String nome, Processo processo) {
        this.nomePrograma = nome;
        this.processo = processo;
        this.contadorPrograma = 0;
        this.ref = UUID.randomUUID();
    }

    public String getNomePrograma() {
        return nomePrograma;
    }

    public UUID getRef() {
        return ref;
    }

    public Estado executaProxInstrucao(){
        LOGGER.info(this.nomePrograma);
        this.processo.executa(this.contadorPrograma);
        this.contadorPrograma++;

        return this.processo.getEstado();
    }

    public void trocaEstado(Estado estado){
        this.processo.setEstado(estado);
    }

    public boolean verificaPronto(){
        if(this.processo.getQuantumBloqueio() == 0){
            this.trocaEstado(Estado.PRONTO);
            return true;
        }
        return false;
    }

    public void decrementaQuantum(){
        LOGGER.info(this.getNomePrograma());
        this.processo.decrementaQuantunsBloqueados();
    }
}
