package org.example.jdbc;

import java.sql.Connection;

public class ConnectionTest {

    public static void main(String[] args) {
        try {
            // Tenta obter uma conexão
            Connection connection = ConnectionFactory.getConnection();

            // Verifica se a conexão não é nula
            if (connection != null) {
                System.out.println("Conexão estabelecida com sucesso!");
            }
        } catch (Exception e) {
            // Em caso de erro, imprime o erro
            System.out.println("Falha ao estabelecer a conexão: " + e.getMessage());
        }
    }
}
