package br.usp.bcp;

import br.usp.processo.Estado;
import br.usp.processo.Processo;

import java.util.List;
import java.util.UUID;

public class BCP {

    private String nomePrograma;

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
        this.processo.executa(this.contadorPrograma);
        this.contadorPrograma++;

        return this.processo.getEstado();
    }
}
