package br.usp.processo;

import org.apache.log4j.Logger;

import java.util.List;

public class Processo {

    private static final Logger LOGGER = Logger.getLogger(Processo.class.getSimpleName());

    private Estado estado = Estado.PRONTO;

    private Integer registradorX = null;

    private Integer registradorY = null;

    private final List<String> instrucoes;

    public Processo(List<String> instrucoes) {
        this.instrucoes = instrucoes;
    }

    private void comando(String instrucao){
        LOGGER.info("instrucao " + instrucao);
        if ("SAIDA".equals(instrucao)) {
            this.estado = Estado.FINALIZADO;
        }
    }

    public void executa (int contador){
        String instrucao = instrucoes.get(contador);
        comando(instrucao);
    }

    public Estado getEstado() {
        return estado;
    }
}
