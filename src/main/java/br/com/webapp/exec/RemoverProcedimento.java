package br.com.webapp.exec;



import br.com.webapp.dao.ProcedimentoDAO;
import br.com.webapp.infra.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class RemoverProcedimento {

	public static void main(String[] args) throws SQLException {

		Connection connection = ConnectionFactory.getConnection();
		ProcedimentoDAO dao = new ProcedimentoDAO(connection);
		dao.delete(1L);

		connection.close();
	}
}
