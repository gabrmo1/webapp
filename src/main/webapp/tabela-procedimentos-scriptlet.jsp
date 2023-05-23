<%@ page import="br.com.webapp.model.Procedimento" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.webapp.model.Genero" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <title>Tabela de Dados</title>
</head>
<body>
<div class="container">
    <h2 class="text-center mb-4">Tabela de Dados</h2>
    <a href="/web-app">Voltar</a>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Procedimento</th>
            <th>Idade</th>
            <th>Gênero</th>
            <th>Permitido</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Procedimento> dados = (List<Procedimento>) request.getAttribute("data");

            for (Procedimento data : dados) {
                String procedimento = data.getNroProcedimento();
                int idade = data.getIdade();
                Genero genero = data.getGenero();
                boolean permitido = data.getPermitido();
        %>
        <tr>
            <td><%= procedimento %>
            </td>
            <td><%= idade %>
            </td>
            <td><%= genero == Genero.MASCULINO ? "Masculino" : genero == Genero.FEMININO ? "Feminino" : "" %>
            </td>
            <td><%= permitido ? "Sim" : "Não" %>
            </td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>
</div>
</body>
</html>
