package br.com.alura.comex.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    public Connection criaConexao() {
        String usuario = "sa";
        String senha = "";

        try {
            String url = "jdbc:h2:./comex;;DB_CLOSE_ON_EXIT=FALSE;"
                    //   + "INIT=runscript from './schema.sql'\\;"
                    //   + "runscript from './data.sql'"
                    ;
            
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao tentar se conectar no banco de dados.");
        }
    }
}
