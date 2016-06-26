package br.ufsc.ine.aps.enuns;


public enum Area {

    SOFTWARE(1, "Software"),
    HARDWARE(2, "Hardware");

    Area(Integer id, String descricao) {
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