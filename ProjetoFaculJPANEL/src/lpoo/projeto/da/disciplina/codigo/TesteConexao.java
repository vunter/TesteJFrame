package lpoo.projeto.da.disciplina.codigo;

import java.sql.*;

public class TesteConexao {
	public Connection connection = null;
	public Statement statement = null;
	public ResultSet resultSet = null;

	public void getConnection() {
		try {

			Class.forName("com.mysql.cj.jdbc.Driver");
			this.connection = DriverManager.getConnection("jdbc:mysql://db4free.net:3306/vunterprogram", "vunter",
					"leoeifert2");
			this.statement = this.connection.createStatement();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} catch (Exception er) {
			System.out.println(er);
		}
	}

	public String estaConectado() {
		if (this.connection != null) {
			return "Conectado com sucesso!";
		} else {
			return "OPS! Algo deu errado, não foi possível conectar ao banco de dados";
		}
	}
}
