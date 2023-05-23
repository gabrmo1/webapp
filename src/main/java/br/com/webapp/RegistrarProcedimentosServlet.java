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

@WebServlet("registrar-procedimentos")
public class RegistrarProcedimentosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/registrar-procedimentos.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Connection connection = ConnectionFactory.getConnection();
        ProcedimentoDAO dao = new ProcedimentoDAO(connection);

        String nroProcedimento = request.getParameter("procedimento");
        Integer idade = Integer.parseInt(request.getParameter("idade"));
        Genero genero = Genero.valueOf(request.getParameter("genero").toUpperCase());
        Boolean permitido = Boolean.valueOf(request.getParameter("permitido"));

        List<Procedimento> procedimentos = dao.findByParams(nroProcedimento, idade, genero);

        if (procedimentos.isEmpty()) {
            dao.save(new Procedimento(null, nroProcedimento, idade, genero, permitido));
        } else {
            throw new RuntimeException("Já existe um procedimento cadastrado com estes dados.");
        }
    }
}
