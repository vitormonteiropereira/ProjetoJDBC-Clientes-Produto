//package org.example.jdbc;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class ConnectionFactory {
//
//
//
//        private static Connection connection;
//
//        private ConnectionFactory(Connection connection) {
//        }
//
//        public static Connection getConnection() throws SQLException {
//            if (connection == null) {
//                connection = initConnection();
//            }
//            else if (connection.isClosed()){
//                connection = initConnection();
//            }
//            return connection;
//        }
//
//    private static Connection initConnection() {
//
//        // URL de conexão JDBC para o PostgreSQL
//        String url = "jdbc:postgresql://localhost:5432/clientes_ebac";
//        String user = "postgres";
//        String password = "admin123";
//
//        try {
//           return DriverManager.getConnection(url, user, password);
//        } catch (SQLException e){
//            throw new RuntimeException(e);
//        }
//    }
//
//
//}

package org.example.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static Connection connection;

    private ConnectionFactory(Connection connection) {
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null) {
            connection = initConnection();
        } else if (connection.isClosed()) {
            connection = initConnection();
        }
        return connection;
    }

    private static Connection initConnection() {
        Properties props = new Properties();
        try (InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new RuntimeException("Arquivo database.properties não encontrado.");
            }
            props.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Erro ao carregar configurações do banco de dados.", ex);
        }

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.username");
        String password = props.getProperty("db.password");

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

