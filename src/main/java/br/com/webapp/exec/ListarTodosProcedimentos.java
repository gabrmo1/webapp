package br.com.webapp.exec;



import br.com.webapp.dao.ProcedimentoDAO;
import br.com.webapp.infra.ConnectionFactory;
import br.com.webapp.model.Procedimento;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class ListarTodosProcedimentos {

	public static void main(String[] args) throws SQLException {

		Connection connection = ConnectionFactory.getConnection();

		ProcedimentoDAO dao = new ProcedimentoDAO(connection);
		List<Procedimento> procedimentos = dao.findAll();

		for (Procedimento procedimento : procedimentos) {
			System.out.println("ID => " + procedimento.getId());
			System.out.println("nroProcedimento => " + procedimento.getNroProcedimento());
			System.out.println("idade => " + procedimento.getIdade());
			System.out.println("genero => " + procedimento.getGenero());
			System.out.println("permitido => " + procedimento.getPermitido());
		}

		connection.close();
	}
}
