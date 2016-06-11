package br.ufsc.ine.aps.enuns;


public enum TipoUsuario {

    CLIENTE(1, "Cliente"), ATENDENTE(2, "Atendente"), OPERADOR_SUPORTE(3, "Operador de suporte"), GERENTE(4, "Gerente");

    private TipoUsuario(Integer id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    private Integer id;
    private String descricao;

}
