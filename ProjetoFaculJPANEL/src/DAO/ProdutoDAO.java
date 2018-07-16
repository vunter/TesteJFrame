package DAO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import lpoo.projeto.da.disciplina.codigo.Conexao;

public class ProdutoDAO {
	
	public void create() {
		Connection con = Conexao.getConnection();
		PreparedStatement stat = null;
		try {

			stat = con.prepareStatement("");
		} catch (SQLException e) {
			Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, e);
		}
	}
}
