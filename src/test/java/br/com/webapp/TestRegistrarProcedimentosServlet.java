package br.com.webapp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.*;

public class TestRegistrarProcedimentosServlet {

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testDoPost() throws IOException {
        when(request.getParameter("procedimento")).thenReturn("12347");
        when(request.getParameter("idade")).thenReturn("10");
        when(request.getParameter("genero")).thenReturn("MASCULINO");
        when(request.getParameter("permitido")).thenReturn("true");

        PrintWriter writer = mock(PrintWriter.class);
        when(response.getWriter()).thenReturn(writer);

        RegistrarProcedimentosServlet myServlet = new RegistrarProcedimentosServlet();
        myServlet.doPost(request, response);

        verify(request).getParameter("procedimento");
        verify(request).getParameter("idade");
        verify(request).getParameter("genero");
        verify(request).getParameter("permitido");
        verify(response.getWriter());
    }

}
