package lpoo.projeto.da.disciplina.codigo;

import java.sql.*;

public class Teste {

	public static void main(String[] args) throws SQLException {
		TesteConexao bd = new TesteConexao();
		bd.getConnection();
		boolean c = true;
		while (c) {
			if ("Conectado com sucesso!".equals(bd.estaConectado())) {
				System.out.println("Conectado?");
				c = false;
			} else {
				System.out.println("Errou");
				c = false;
			}
		}

	}

}
