package br.com.webapp.exec;


import br.com.webapp.dao.ProcedimentoDAO;
import br.com.webapp.infra.ConnectionFactory;
import br.com.webapp.model.Genero;
import br.com.webapp.model.Procedimento;

import java.sql.Connection;
import java.sql.SQLException;

public class CriarProcedimento {

    public static void main(String[] args) throws SQLException {

        Connection connection = ConnectionFactory.getConnection();

        String nroProcedimento = "109298398";
        Integer idade = 16;
        Genero genero = Genero.MASCULINO;
        Boolean permitido = true;

        Procedimento procedimento = new Procedimento(null, nroProcedimento, idade, genero, permitido);

        ProcedimentoDAO dao = new ProcedimentoDAO(connection);
        dao.save(procedimento);

        connection.close();
    }
}
