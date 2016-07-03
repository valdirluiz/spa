package br.ufsc.ine.aps.controllers.protocolo;

import br.ufsc.ine.aps.controllers.cliente.ControllerCliente;
import br.ufsc.ine.aps.controllers.funcionario.ControllerFuncionario;
import br.ufsc.ine.aps.controllers.interacao.ControllerInteracao;
import br.ufsc.ine.aps.controllers.usuario.ControllerUsuario;
import br.ufsc.ine.aps.enuns.Area;
import br.ufsc.ine.aps.enuns.Categoria;
import br.ufsc.ine.aps.enuns.Status;
import br.ufsc.ine.aps.enuns.TipoInteracao;
import br.ufsc.ine.aps.exceptions.LimiteProtocoloExedido;
import br.ufsc.ine.aps.exceptions.ProtocoloJaCancelado;
import br.ufsc.ine.aps.exceptions.StatusEmAndamento;
import br.ufsc.ine.aps.models.Cliente;
import br.ufsc.ine.aps.models.Pessoa;
import br.ufsc.ine.aps.models.Protocolo;

import java.util.Calendar;
import java.util.List;

public class ControllerProtocolo {

    private DaoProtocolo daoProtocolo;
    private ControllerFuncionario controllerFuncionario;
    private ControllerInteracao controllerInteracao;
    private ControllerUsuario controllerUsuario;
    private ControllerCliente controllerCliente;

    public static ControllerProtocolo getInstance() {
        return ourInstance;
    }
    private static ControllerProtocolo ourInstance = new ControllerProtocolo();

    private  ControllerProtocolo(){
        this.daoProtocolo = DaoProtocolo.getInstance();
        this.controllerFuncionario = ControllerFuncionario.getInstance();
        this.controllerInteracao = ControllerInteracao.getInstance();
        this.controllerUsuario = ControllerUsuario.getInstance();
        this.controllerCliente = ControllerCliente.getInstance();

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
        protocolo.setDataCriacao(Calendar.getInstance());
        protocolo.setMensagemLivre(descricao);
        this.defineResponsavel(protocolo);
        this.daoProtocolo.salvar(protocolo);
        GeradorDeIdentificador.getInstance().geraIdentificador(protocolo);
        this.daoProtocolo.savarIdentificador(protocolo);
        controllerInteracao.addInteracao(protocolo, TipoInteracao.CRIACAO);
    }

    private void defineResponsavel(Protocolo protocolo) {
        Pessoa responsavel = null;
        Integer qtSemelhantes = this.daoProtocolo.findSemelhantes(protocolo);
        //igual a 2 para o terceiro ir para o gerente conforme RN03
        if(qtSemelhantes>=2){
            responsavel = this.controllerFuncionario.findGerente();
        } else{
            responsavel = this.controllerFuncionario.findOperadorDisponivel();
        }

        if(responsavel!=null){
            protocolo.setResponsavel(responsavel);
        }
    }

    public void cancelarProtocolo(Protocolo protocolo) throws StatusEmAndamento, ProtocoloJaCancelado {
        //TODO: não te mais a coluna motivoCancelamento
        if(protocolo.getStatus().equals(Status.CANCELADO)){
            throw new ProtocoloJaCancelado();
        }

        if(!protocolo.getStatus().equals(Status.AGUARDANDO_ATENDIMENTO)){
            throw new StatusEmAndamento();
        }
        protocolo.setStatus(Status.CANCELADO);
        this.daoProtocolo.updateStatus(protocolo);
        this.controllerInteracao.addInteracao(protocolo, TipoInteracao.CANCELAR);
    }

    public List<Protocolo> buscaProtocolos(){
        return this.daoProtocolo.buscaProtocolos();
    }

    public List<Protocolo> listarProtocolos(){
        List<Protocolo> protocolos = daoProtocolo.list();
        for(Protocolo protocolo : protocolos){
            Pessoa pessoa = this.controllerUsuario.findUsuarioById(protocolo.getResponsavel().getId());
            Cliente cliente = this.controllerCliente.findById(protocolo.getCliente().getId());
            protocolo.setResponsavel(pessoa);
            protocolo.setCliente(cliente);
        }
        return protocolos;
    }


    public void iniciarAtendimento(Protocolo protocolo) {
        if(protocolo.getStatus().equals(Status.AGUARDANDO_ATENDIMENTO)){
            protocolo.setStatus(Status.EM_ATENDIMENTO);
            this.controllerInteracao.addInteracao(protocolo, TipoInteracao.ATENDIMENTO);
            this.daoProtocolo.updateStatus(protocolo);
        }
    }
}
