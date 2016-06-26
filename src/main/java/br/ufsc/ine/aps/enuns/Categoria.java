package br.ufsc.ine.aps.enuns;


public enum Categoria {

    SUGESTAO(1, "Sugeståo"),
    RECLAMACAO(2, "Reclamação"),
    DUVIDA(3, "Duvida");

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
}
