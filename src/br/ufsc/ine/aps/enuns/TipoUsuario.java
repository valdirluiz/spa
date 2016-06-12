package br.ufsc.ine.aps.enuns;


public enum TipoUsuario {

    CLIENTE(1, "Cliente"), ATENDENTE(2, "Atendente"), OPERADOR_SUPORTE(3, "Operador de suporte"), GERENTE(4, "Gerente");

    private TipoUsuario(Integer id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    private Integer id;
    private String descricao;

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoUsuario  getById(Integer id){
        switch (id){
            case 1:
                return CLIENTE;
            case 2:
                return ATENDENTE;
            case 3:
                return OPERADOR_SUPORTE;
            case 4:
                return GERENTE;
            default:
                return null;
        }
    }
}
