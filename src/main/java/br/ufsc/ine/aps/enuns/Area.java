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

    public static Area findByDescricao(String area){
        switch (area){
            case "Software":
                return SOFTWARE;
            case "Hardware":
                return HARDWARE;
            default:
                return null;
        }
    }

    public static Area getById(int id) {
        switch (id) {
            case 1:
                return SOFTWARE;
            case 2:
                return HARDWARE;
            default:
                return null;
        }
    }
}
