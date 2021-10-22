package br.usp.processo;

import org.apache.log4j.Logger;

import java.util.List;

public class Processo {

    private static final Logger LOGGER = Logger.getLogger(Processo.class.getSimpleName());

    private Estado estado = Estado.PRONTO;

    private Integer registradorX = null;

    private Integer registradorY = null;

    private Integer quantumBloqueados = 0;

    private final List<String> instrucoes;

    public Processo(List<String> instrucoes) {
        this.instrucoes = instrucoes;
    }

    private void comando(String instrucao){
        LOGGER.info("instrucao " + instrucao);

        switch(Instrucao.fromString(instrucao)){
            case COM:
                break;
            case ES:
                this.estado = Estado.BLOQUEADO;
                this.quantumBloqueados = 2;
                break;
            case X:
                this.registradorX = Integer.parseInt(instrucao.split("=")[1]);
                break;
            case Y:
                this.registradorY = Integer.parseInt(instrucao.split("=")[1]);
                break;
            case SAIDA:
                this.estado = Estado.FINALIZADO;
                break;
        };

    }

    public void executa(int contador){
        String instrucao = instrucoes.get(contador);
        this.estado = Estado.EXECUTANDO;
        comando(instrucao);
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Integer getQuantumBloqueados() {
        return quantumBloqueados;
    }

    public void setQuantumBloqueados(Integer quantumBloqueados) {
        this.quantumBloqueados = quantumBloqueados;
    }

    public void decrementaQuantunsBloqueados(){
        this.quantumBloqueados--;
    }
}
