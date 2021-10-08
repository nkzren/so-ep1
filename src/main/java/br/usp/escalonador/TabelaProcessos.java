package br.usp.escalonador;

import br.usp.bcp.BCP;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TabelaProcessos {

    private Map<UUID, BCP> processos = new HashMap<>();

    public void adicionaProcesso(BCP bcp) {
        processos.put(bcp.getRef(), bcp);
    }
}
