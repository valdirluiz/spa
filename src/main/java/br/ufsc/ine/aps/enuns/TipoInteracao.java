package br.ufsc.ine.aps.enuns;

import br.ufsc.ine.aps.models.Interacao;

public enum TipoInteracao {
    CRIACAO(1, "Criação"), FEEDBACK(2, "Feedback"), ANTENDIMENTO(3,"Atendimento"), CONCLUSAO(4, "Conclusão"), CANCELAR(5, "Cancelar");


    private TipoInteracao(Integer id, String descricao){
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
