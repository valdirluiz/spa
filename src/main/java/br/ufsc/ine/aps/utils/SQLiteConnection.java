package br.ufsc.ine.aps.utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SQLiteConnection {


    private Connection connection;


    private static SQLiteConnection ourInstance = new SQLiteConnection();

    public static SQLiteConnection getInstance() {
        return ourInstance;
    }

    private SQLiteConnection() {
        this.openConnection();
    }

    private void openConnection(){
        try {
            String bd = new File("data.bd").getAbsolutePath();
            System.out.println(bd);
            Class.forName("org.sqlite.JDBC");
            this.connection =  DriverManager.getConnection("jdbc:sqlite:" + bd);
            System.out.println(connection.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static void main(String[] args) {
        try {
            SQLiteConnection sQLiteConnection = SQLiteConnection.getInstance();
            Connection connection = sQLiteConnection.getConnection();
            System.out.println(connection.getSchema());
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT * FROM pessoas;" );
            while ( rs.next() ) {
                int id = rs.getInt("id");
                String  nome = rs.getString("nome");

                System.out.println( "ID = " + id );
                System.out.println( "NAME = " + nome );
                System.out.println();
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
