package br.com.livro.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	public BaseDAO() {
		try {
			// Necess�rio para utilizar o driver JDBC do MySQL
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// Erro de driver JDBC (adicione o driver .jar do MySQL em
			// /WEB-INF/lib)
			e.printStackTrace();
		}
	}

	protected Connection getConnection() throws SQLException {
		// URL de conex�o com o banco de dados
		String url = "jdbc:mysql://localhost/politicos";
		// Conecta utilizando a URL, usu�rio e senha.
		Connection conn = DriverManager.getConnection(url, "root", "password");
		return conn;
	}

	public static void main(String[] args) throws SQLException {
		BaseDAO db = new BaseDAO();
		// Testa a conex�o
		Connection conn = db.getConnection();
		System.out.println(conn);
	}
}
