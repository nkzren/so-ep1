package br.usp.escalonador;

import br.usp.bcp.BCP;

import java.util.HashMap;
import java.util.Map;

public class TabelaProcessos {

    private Map<String, BCP> processos = new HashMap<>();

    public void adicionaProcesso(BCP bcp) {
        processos.put(bcp.getRef(), bcp);
    }
}
