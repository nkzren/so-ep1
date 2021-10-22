package br.usp.processo;

import org.apache.log4j.Logger;

import java.util.List;

public class Processo {

    private static final Logger LOGGER = Logger.getLogger(Processo.class.getSimpleName());

    private Estado estado = Estado.PRONTO;

    private Integer registradorX = 0;

    private Integer registradorY = 0;

    private Integer quantumBloqueio = 0;

    private final List<String> instrucoes;

    public Processo(List<String> instrucoes) {
        this.instrucoes = instrucoes;
    }

    /**
     * Funcao que interpreta as instrucoes recebidas e modifica o processo
     * @param instrucao a instrucao
     */
    private void comando(String instrucao){
        switch(Instrucao.fromString(instrucao)){
            case COM:
                break;
            case ES:
                this.estado = Estado.BLOQUEADO;
                this.quantumBloqueio = 2;
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
        }
        LOGGER.info("EXECUTANDO INSTRUCAO: " + instrucao + " \t|    " + this);

    }

    /**
     * Funcao que recupera a instrucao e inicia o processo de interpreta-lo
     * @param contador Contador de instrucao a ser executada
     */
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

    public Integer getQuantumBloqueio() {
        return quantumBloqueio;
    }

    public void decrementaQuantunsBloqueados(){
        this.quantumBloqueio--;
        LOGGER.info(" DECREMENTANDO QUANTA - " + this.getQuantumBloqueio() + " \t|    " + this);
    }

    @Override
    public String toString() {
        return "Processo {" +
                "estado=" + estado +
                ", registradorX=" + registradorX +
                ", registradorY=" + registradorY +
                "}";
    }
}
