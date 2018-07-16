package lpoo.projeto.da.disciplina.codigo;

import java.sql.*;
import java.util.*;

public class Conexao {
	static String status = "";
	public Connection connection = null;
	public Statement statement = null;
	public ResultSet resultSet = null;
	private static final String servidor = "jdbc:mysql://db4free.net:3306/vunterprogram?useSSL=false";
	private static final String usuario = "vunter";
	private static final String senha = "leoeifert2";

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(servidor, usuario, senha);
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException("Erro ao conectar: " + e);
		}
	}

	public void conectar() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection(servidor, usuario, senha);
			this.statement = this.connection.createStatement();
		} catch (SQLException e) {
			System.out.println("erro de sql: " + e);
		} catch (Exception e) {
			System.out.println("Não foi possivel conectar no banco de dados! Erro: " + e);
		}
	}

	public void desconectar() {
		try {
			this.connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String estaConectado() {
		if (this.connection != null) {
			return "Conectado com sucesso!";
		} else {
			return "OPS! Algo deu errado, não foi possível conectar ao banco de dados";
		}
	}

	public void listarNomes() {
		try {
			String query = "SELECT * FROM clientes ORDER BY nome";
			this.resultSet = this.statement.executeQuery(query);
			while (this.resultSet.next()) {
				System.out.println(
						"Nome: " + this.resultSet.getString("nome") + " " + "CPF: " + this.resultSet.getString("cpf"));
			}
		} catch (Exception e) {

		}
	}

	public void cadastrarCliente(String cpf, String nome, int idade, String dataNascimento, int pontos) {
		try {
			String query = "INSERT INTO clientes (cpf, nome, idade, dataNascimento, ativo, pontos) VALUES ('" + cpf
					+ "', '" + nome + "', '" + idade + "', '" + dataNascimento + "', '1', '" + pontos + "');";

			this.statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void descadastrarCliente(String cpf) {

		try {
			String queryDelete = "DELETE FROM Clientes WHERE cpf = '" + cpf + "';";
			this.statement.executeUpdate(queryDelete);
			System.out.println("Cliente descadrastado com sucesso!");
		} catch (Exception e) {
			System.out.println("Erro ao descadastrar cliente: " + e.getMessage());
		}

	}

	public void statusCliente(String cpf) {
		try {
			String query = "SELECT * FROM Clientes WHERE cpf = '" + cpf + "';";
			this.resultSet = this.statement.executeQuery(query);

			while (this.resultSet.next()) {
				System.out.println(
						"CPF: " + this.resultSet.getString("cpf") + "\nNome: " + this.resultSet.getString("nome")
								+ "\nData de Nascimento/Idade: " + this.resultSet.getString("dataNascimento") + " / "
								+ this.resultSet.getInt("idade") + " Anos\nAtivo no sistema de pontos: "
								+ this.resultSet.getBoolean("ativo") + "\nPontos: " + this.resultSet.getInt("pontos"));

			}

		} catch (SQLException e) {
			System.out.println("Erro de SQL: " + e);
		} catch (Exception e) {
			System.out.println("Erro! Cliente não encontrado no banco de dados! " + e.getMessage());
		}
	}

	public void pesquisarClientePorNome(String nome) {
		try {
			String query = "SELECT * FROM Clientes WHERE nome LIKE '" + nome + "%';";
			this.resultSet = this.statement.executeQuery(query);
			while (this.resultSet.next()) {
				System.out.println(
						"CPF: " + this.resultSet.getString("cpf") + "\nNome: " + this.resultSet.getString("nome")
								+ "\nData de Nascimento/Idade: " + this.resultSet.getString("dataNascimento") + " / "
								+ this.resultSet.getInt("idade") + " Anos\nAtivo no sistema de pontos: "
								+ this.resultSet.getBoolean("ativo") + "\nPontos: " + this.resultSet.getInt("pontos")
								+ "\n================================================================");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
