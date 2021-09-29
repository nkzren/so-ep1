package br.usp.bcp;

import br.usp.processo.Processo;

import java.util.List;

public class BCP {

    private final String ref;

    private String nomePrograma;

    private int contadorPrograma;

    private final Processo processo;

    /**
     * Funcao contendo toda a inicializacao do BCP a partir das linhas do arquivo de texto lido
     * @param instrucoes lista de instrucoes do arquivo
     * @return BCP inicializado
     */
    public static BCP of(List<String> instrucoes) {

        // FIXME: pensar num jeito bom de gerar a referencia
        return new BCP("lalala", new Processo(instrucoes));
    }

    private BCP(String ref, Processo processo) {
        this.ref = ref;
        this.processo = processo;
        this.contadorPrograma = 0;
    }

    public Processo getProcesso() {
        return this.processo;
    }

    public String getRef() {
        return this.ref;
    }
}
