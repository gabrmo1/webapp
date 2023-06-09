package br.com.webapp;

import br.com.webapp.dao.ProcedimentoDAO;
import br.com.webapp.infra.ConnectionFactory;
import br.com.webapp.model.Genero;
import br.com.webapp.model.Procedimento;
import br.com.webapp.service.ConsultarProcedimentosService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

@WebServlet("/consultar-procedimentos")
public class ConsultarProcedimentosServlet extends HttpServlet {

    @Inject
    ConsultarProcedimentosService service;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = ConnectionFactory.getConnection();
        ProcedimentoDAO dao = new ProcedimentoDAO(connection);

        String nroProcedimentoStr = request.getParameter("procedimento");
        String idadeStr = request.getParameter("idade");
        String generoStr = request.getParameter("genero");

        if (nroProcedimentoStr == null && idadeStr == null && generoStr == null) {
            List<Procedimento> dados = dao.findAll();

            request.setAttribute("data", dados);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/tabela-procedimentos-scriptlet.jsp");
            requestDispatcher.forward(request, response);

        } else {
            Map<String, String> validacoes = service.validarDados(nroProcedimentoStr, idadeStr, generoStr);

            if (validacoes.isEmpty()) {
                Integer idadeReq = idadeStr != null ? Integer.parseInt(idadeStr) : null;
                Genero generoReq = generoStr != null ? Genero.valueOf(generoStr.toUpperCase()) : null;

                List<Procedimento> dados = dao.findByParams(nroProcedimentoStr, idadeReq, generoReq);

                if (dados.isEmpty()) {
                    request.setAttribute("validation", "* Nenhum procedimento encontrado");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/");
                    requestDispatcher.forward(request, response);
                } else {
                    request.setAttribute("data", dados);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/tabela-procedimentos-scriptlet.jsp");
                    requestDispatcher.forward(request, response);
                }

            } else {
                validacoes.forEach(request::setAttribute);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/");
                requestDispatcher.forward(request, response);
            }

        }

    }

}