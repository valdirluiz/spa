package br.ufsc.ine.aps.assets;

import java.io.InputStream;

/**
 * Created by Valdir Luiz on 19/06/2016.
 */
public class Assets {


    public static InputStream deleteImage(){
        return Assets.class.getResourceAsStream("delete.png");
    }

}
