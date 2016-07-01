package br.ufsc.ine.aps.controllers.protocolo;

import br.ufsc.ine.aps.models.Protocolo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Valdir Luiz on 30/06/2016.
 */
public class GeradorDeIdentificador {
    private static GeradorDeIdentificador ourInstance = new GeradorDeIdentificador();

    public static GeradorDeIdentificador getInstance() {
        return ourInstance;
    }

    private SimpleDateFormat dateFormat;

    private GeradorDeIdentificador() {
        dateFormat = new SimpleDateFormat("yyyyMMdd");
    }

    public void geraIdentificador(Protocolo protocolo){
        StringBuilder builder = new StringBuilder();
        builder.append(dateFormat.format(new Date()));
        builder.append(protocolo.getId());
        protocolo.setIdentificador(builder.toString());
    }
}
