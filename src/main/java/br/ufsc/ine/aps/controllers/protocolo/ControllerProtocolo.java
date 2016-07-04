package br.ufsc.ine.aps.controllers.protocolo;

import br.ufsc.ine.aps.controllers.cliente.ControllerCliente;
import br.ufsc.ine.aps.controllers.funcionario.ControllerFuncionario;
import br.ufsc.ine.aps.controllers.interacao.ControllerInteracao;
import br.ufsc.ine.aps.controllers.login.Autenticador;
import br.ufsc.ine.aps.controllers.usuario.ControllerUsuario;
import br.ufsc.ine.aps.enuns.Area;
import br.ufsc.ine.aps.enuns.Categoria;
import br.ufsc.ine.aps.enuns.Status;
import br.ufsc.ine.aps.enuns.TipoInteracao;
import br.ufsc.ine.aps.exceptions.LimiteProtocoloExedido;
import br.ufsc.ine.aps.exceptions.ProtocoloJaCancelado;
import br.ufsc.ine.aps.exceptions.SemRespostaPreenchida;
import br.ufsc.ine.aps.exceptions.StatusEmAndamento;
import br.ufsc.ine.aps.models.Cliente;
import br.ufsc.ine.aps.models.Gerente;
import br.ufsc.ine.aps.models.Pessoa;
import br.ufsc.ine.aps.models.Protocolo;

import java.util.*;
import java.util.stream.Collectors;

public class ControllerProtocolo {

    private DaoProtocolo daoProtocolo;
    private ControllerFuncionario controllerFuncionario;
    private ControllerInteracao controllerInteracao;
    private ControllerUsuario controllerUsuario;
    private ControllerCliente controllerCliente;

    private Autenticador autenticador;

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
        this.autenticador = Autenticador.getInstance();
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
        Protocolo protocolo = new Protocolo(Area.findByDescricao(area), Categoria.findByDescricao(categoria), cliente, Status.AGUARDANDO_ATENDIMENTO, Calendar.getInstance(), descricao);
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

    public boolean desabilitarInicializar(Integer idOperador){
        Integer total = this.daoProtocolo.buscaProtocolosDoOperador(idOperador);
        return total>1;
    }

    public List<Protocolo> buscaProtocolos(){
        return this.daoProtocolo.buscaProtocolos();
    }



    public List<Protocolo> listarProtocolos(Map<String, Object> filtros){
        List<Protocolo> all = daoProtocolo.list();
        for(Protocolo protocolo : all){
            Pessoa pessoa = this.controllerUsuario.findUsuarioById(protocolo.getResponsavel().getId());
            Cliente cliente = this.controllerCliente.findById(protocolo.getCliente().getId());
            protocolo.setResponsavel(pessoa);
            protocolo.setCliente(cliente);
        }

       if(filtros==null || filtros.isEmpty()){
            return all;
        } else {
            if(filtros.containsKey("idOperador")){
                return all.stream().filter(p -> p.getResponsavel()==null || p.getResponsavel().getId().equals(filtros.get("idOperador"))).collect(Collectors.toList());
            }

            if(filtros.containsKey("idCliente")){
                return all.stream().filter(p -> p.getCliente().getId().equals(filtros.get("idCliente"))).collect(Collectors.toList());
            }

            if(filtros.containsKey("idGerente")){
                return all.stream().filter(p -> p.getResponsavel()!= null && p.getResponsavel().getId().equals(filtros.get("idGerente"))).collect(Collectors.toList());
            }
        }

         return  all;
    }


    public void iniciarAtendimento(Protocolo protocolo) throws Exception {
        if(protocolo.getStatus().equals(Status.AGUARDANDO_ATENDIMENTO)){
            protocolo.setStatus(Status.EM_ATENDIMENTO);
            protocolo.setDataInicioExecucao(new Date());
            if (protocolo.getResponsavel()==null){
                Pessoa responsavel = this.controllerUsuario.findUsuarioById(this.autenticador.getUsuarioLogado().getId());
                protocolo.setResponsavel(responsavel);
            }
            this.controllerInteracao.addInteracao(protocolo, TipoInteracao.ATENDIMENTO);
            this.daoProtocolo.iniciarAtendimento(protocolo);
        }
    }

    public void finalizarProtocolo(Protocolo protocolo) throws Exception {
        if(protocolo.getResposta()==null || protocolo.getResposta().isEmpty()){
            throw new SemRespostaPreenchida();
        }
        protocolo.setDataFimExecucao(new Date());
        protocolo.setStatus(Status.AGUARDANDO_FEEDBACK);
        this.daoProtocolo.finalizarAtendimento(protocolo);
        this.controllerInteracao.addInteracao(protocolo, TipoInteracao.CONCLUSAO);
    }

    public Protocolo findById(Integer id) {
        Optional<Protocolo> optional = daoProtocolo.list().stream().filter(p -> p.getId().equals(id)).findFirst();
        if (optional.isPresent()){
            Protocolo protocolo = optional.get();

            protocolo.setCliente(this.controllerCliente.findById(protocolo.getCliente().getId()));
            return  protocolo;
        }

        return null;
    }

    public void inserirFeedback(Protocolo protocolo) throws Exception {
        this.daoProtocolo.inserirFeedback(protocolo);
    }

    public void direcionarParaGerente(Protocolo protocolo) throws Exception {
        Pessoa gerente = this.controllerFuncionario.findGerente();
        protocolo.setResponsavel(gerente);
        this.daoProtocolo.atualizarResponsavel(protocolo);
    }
}
