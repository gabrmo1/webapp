package br.com.webapp.exec;

import br.com.webapp.dao.ProcedimentoDAO;
import br.com.webapp.infra.ConnectionFactory;
import br.com.webapp.model.Procedimento;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class AtualizarProcedimento {

    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionFactory.getConnection();

        ProcedimentoDAO dao = new ProcedimentoDAO(connection);
        Optional<Procedimento> optional = dao.findById(1L);

        optional.ifPresent(it -> {
//			it.setId();
//			it.setNroProcedimento();
//			it.setIdade();
//			it.setGenero();
//			it.setPermitido();
            dao.update(it);
        });

        connection.close();
    }
}
