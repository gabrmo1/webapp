package br.com.webapp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

public class TestConsultarProcedimentosServlet {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher requestDispatcher;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoGet() throws ServletException, IOException {
        when(request.getParameter("procedimento")).thenReturn("1234");
        when(request.getParameter("idade")).thenReturn("10");
        when(request.getParameter("genero")).thenReturn("MASCULINO");

        PrintWriter writer = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);

        when(request.getRequestDispatcher("/tabela-procedimentos-scriptlet.jsp")).thenReturn(requestDispatcher);

        ConsultarProcedimentosServlet myServlet = new ConsultarProcedimentosServlet();
        myServlet.doGet(request, response);

        verify(request).getParameter("procedimento");
        verify(request).getParameter("idade");
        verify(request).getParameter("genero");
        verify(response.getWriter());
    }

}
