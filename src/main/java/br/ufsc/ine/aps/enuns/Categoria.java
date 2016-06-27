package br.ufsc.ine.aps.enuns;


public enum Categoria {

    SUGESTAO(1, "Sugeståo"),
    RECLAMACAO(2, "Reclamação"),
    DUVIDA(3, "Dúvida");

    Categoria(Integer id, String descricao) {
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

    public static Categoria findByDescricao(String descricao){
        switch (descricao){
            case "Sugeståo" :
                return SUGESTAO;
            case "Reclamação":
                return RECLAMACAO;
            case "Dúvida":
                return  DUVIDA;
            default:
                return null;
        }
    }
}
