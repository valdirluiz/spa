package br.ufsc.ine.aps.enuns;

public enum TipoInteracao {
    CRIACAO(1, "Criação"), FEEDBACK(2, "Feedback"), ATENDIMENTO(3,"Atendimento"), CONCLUSAO(4, "Conclusão"), CANCELAR(5, "Cancelar"), DIRECIONAR(6, "Direcionar");


    private TipoInteracao(Integer id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    public static TipoInteracao findById(Integer id){
        switch (id){
            case 1:
                return CRIACAO;
            case 2:
                return FEEDBACK;
            case 3:
                return ATENDIMENTO;
            case 4:
                return CONCLUSAO;
            case 5:
                return CANCELAR;
            case  6:
                return DIRECIONAR;
            default:
                return null;
        }
    }

    private Integer id;
    private String descricao;

    public Integer getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }
}
