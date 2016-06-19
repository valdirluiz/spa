package br.ufsc.ine.aps.controllers.funcionario;

/**
 * Created by Valdir Luiz on 19/06/2016.
 */
public class ControllerFuncionario {
    private static ControllerFuncionario ourInstance = new ControllerFuncionario();

    public static ControllerFuncionario getInstance() {
        return ourInstance;
    }

    private ControllerFuncionario() {
    }
}
