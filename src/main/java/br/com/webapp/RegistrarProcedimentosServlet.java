package br.com.webapp;

import br.com.webapp.dao.ProcedimentoDAO;
import br.com.webapp.infra.ConnectionFactory;
import br.com.webapp.model.Procedimento;
import br.com.webapp.service.RegistrarProcedimentosService;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

@WebServlet("registrar-procedimentos")
public class RegistrarProcedimentosServlet extends HttpServlet {

    @Inject
    private RegistrarProcedimentosService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/registrar-procedimentos.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Connection connection = ConnectionFactory.getConnection();
        ProcedimentoDAO dao = new ProcedimentoDAO(connection);
        PrintWriter writer = response.getWriter();
        int responseStatusCode = 201;

        Procedimento procedimento = service.getProcedimentoObjectFromRequest(request);
        List<String> validations = service.validarProcedimento(procedimento);

        if (validations.isEmpty()) {
            List<Procedimento> procedimentosIguais = dao.findByParams(procedimento);

            if (procedimentosIguais.isEmpty()) {
                dao.save(procedimento);
                writer.write("Cadastro realizado com sucesso");
            } else {
                responseStatusCode = 500;
                writer.write("Já existe um procedimento cadastrado com estes dados.");
            }

        } else {
            writer.println("Problemas encontrados:\n");

            for (String validation : validations) {
                writer.println(" - " + validation + ".");
            }

            responseStatusCode = 500;
        }

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(responseStatusCode);
    }

}
