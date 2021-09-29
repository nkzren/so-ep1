package br.usp.processo;

import java.util.List;

public class Processo {

    private Estado estado = Estado.PRONTO;

    private Integer registradorX = null;

    private Integer registradorY = null;

    private final List<String> instrucoes;

    public Processo(List<String> instrucoes) {
        this.instrucoes = instrucoes;
    }

}
