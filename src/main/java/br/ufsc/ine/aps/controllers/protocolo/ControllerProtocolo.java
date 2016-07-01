package br.ufsc.ine.aps.controllers.protocolo;

import br.ufsc.ine.aps.controllers.funcionario.ControllerFuncionario;
import br.ufsc.ine.aps.controllers.interacao.ControllerInteracao;
import br.ufsc.ine.aps.enuns.Area;
import br.ufsc.ine.aps.enuns.Categoria;
import br.ufsc.ine.aps.enuns.Status;
import br.ufsc.ine.aps.enuns.TipoInteracao;
import br.ufsc.ine.aps.exceptions.LimiteProtocoloExedido;
import br.ufsc.ine.aps.models.Cliente;
import br.ufsc.ine.aps.models.Pessoa;
import br.ufsc.ine.aps.models.Protocolo;

import java.util.Date;

public class ControllerProtocolo {

    private DaoProtocolo daoProtocolo;

    private ControllerFuncionario controllerFuncionario;

    private ControllerInteracao controllerInteracao;

    private static ControllerProtocolo ourInstance = new ControllerProtocolo();

    public static ControllerProtocolo getInstance() {
        return ourInstance;
    }

    private  ControllerProtocolo(){
        this.daoProtocolo = DaoProtocolo.getInstance();
        this.controllerFuncionario = ControllerFuncionario.getInstance();
        this.controllerInteracao = ControllerInteracao.getInstance();
    }

    public String adicionar(Cliente cliente, String categoria, String area, String descricao) throws Exception {
        String notificacao = "Protocolo cadastrado com sucesso";

        Integer totalEmAberto = daoProtocolo.findAbertosByCliente(cliente);
        if(totalEmAberto<3){
            this.cadastrar(cliente, categoria, area, descricao);
        } else{
           throw new LimiteProtocoloExedido();
        }

        return notificacao;
    }

    private void cadastrar(Cliente cliente, String categoria, String area, String descricao) throws Exception {
        Protocolo protocolo = new Protocolo();
        protocolo.setArea(Area.findByDescricao(area));
        protocolo.setCategoria(Categoria.findByDescricao(categoria));
        protocolo.setCliente(cliente);
        protocolo.setStatus(Status.AGUARDANDO_ATENDIMENTO);
        protocolo.setDataCriacao(new Date());
        protocolo.setMensagemLivre(descricao);
        this.defineResponsavel(protocolo);
        this.daoProtocolo.salvar(protocolo);
        controllerInteracao.addInteracao(protocolo, TipoInteracao.CRIACAO);
    }

    private void defineResponsavel(Protocolo protocolo) {
        Pessoa responsavel = null;
        Integer qtSemelhantes = this.daoProtocolo.findSemelhantes(protocolo);
        if(qtSemelhantes>=3){
            responsavel = this.controllerFuncionario.findGerente();
        } else{
            responsavel = this.controllerFuncionario.findOperadorDisponivel();
        }

        if(responsavel!=null){
            protocolo.setResponsavel(responsavel);
        }
    }

}
