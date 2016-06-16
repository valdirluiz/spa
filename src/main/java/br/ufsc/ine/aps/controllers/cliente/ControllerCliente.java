package br.ufsc.ine.aps.controllers.cliente;

public class ControllerCliente {

    private static ControllerCliente instance = new ControllerCliente();

    private ControllerCliente() {}

    public static ControllerCliente getInstance() {
        return instance;
    }

    public void test(String a, String b) {
        System.out.println(a);
        System.out.println(b);
    }

}
