package br.com.webapp;

import br.com.webapp.dao.ProcedimentoDAO;
import br.com.webapp.infra.ConnectionFactory;
import br.com.webapp.model.Genero;
import br.com.webapp.model.Procedimento;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/consultar-procedimentos")
public class ConsultaProcedimentoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getConnection();
        ProcedimentoDAO dao = new ProcedimentoDAO(connection);

        String nroProcedimentoStr = request.getParameter("procedimento");
        String idadeStr = request.getParameter("idade");
        String generoStr = request.getParameter("genero");

        Integer idadeReq = idadeStr != null ? Integer.parseInt(idadeStr) : null;
        Genero generoReq = generoStr != null ? Genero.valueOf(generoStr.toUpperCase()) : null;

        List<Procedimento> dados;

        if (nroProcedimentoStr != null && idadeReq != null && generoReq != null) {
            dados = dao.findByParams(nroProcedimentoStr, idadeReq, generoReq);
        } else {
            dados = dao.findAll();
        }

        if (dados.isEmpty()) {
            request.setAttribute("validation", "* Nenhum procedimento encontrado");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/");
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("data", dados);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/tabela-procedimentos-scriptlet.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}

