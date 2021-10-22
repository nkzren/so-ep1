package br.usp.processo;

public enum Instrucao {
    COM("COM"), SAIDA("SAIDA"), ES("E/S"), X("X="), Y("Y=");
    String instrucao;

    Instrucao(String instrucao){
        this.instrucao = instrucao;
    }

    static Instrucao fromString(String instrucao){
        for (Instrucao i: values()) {
            if(instrucao.startsWith(i.instrucao)){
                return i;
            }
        }
        return null;
    }
}
