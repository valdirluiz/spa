package br.ufsc.ine.aps.enuns;


public enum Status {

    AGUARDANDO_ATENDIMENTO(1, "Aguardando atendimento"),
    EM_ATENDIMENTO(2, "Em atendimento"),
    AGUARDANDO_FEEDBACK(3, "Aguardando feedback"),
    CANCELADO(4, "Cancelado"),
    FINALIZADO(5, "Finalizado");

    private Status(Integer id, String descricao) {
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

    public static Status getById(Integer id){
        switch (id){
            case 1:
                return AGUARDANDO_ATENDIMENTO;
            case 2:
                return EM_ATENDIMENTO;
            case 3:
                return AGUARDANDO_FEEDBACK;
            case 4:
                return CANCELADO;
            case 5:
                return FINALIZADO;
            default:
                return null;
        }
    }
}
