<%@ page import="br.com.webapp.model.Procedimento" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.webapp.model.Genero" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/css/bootstrap.min.css" integrity="sha512-T584yQ/tdRR5QwOpfvDfVQUidzfgc2339Lc8uBDtcp/wYu80d7jwBgAxbyMh0a9YM9F8N3tdErpFI8iaGx6x5g==" crossorigin="anonymous" referrerpolicy="no-referrer">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/4.6.1/js/bootstrap.min.js" integrity="sha512-UR25UO94eTnCVwjbXozyeVd6ZqpaAE9naiEUBK/A+QDbfSTQFhPGj5lOR6d8tsgbBk84Ggb5A3EkjsOgPRPcKA==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
    <link rel="stylesheet" href="css/tabela-procedimentos.css">
    <script src="js/tabela-procedimentos.js"></script>
    <title>Tabela de Dados</title>
</head>
<body>
<div class="container">
    <h1 class="text-center mb-4"><b>Procedimentos Cadastrados</b></h1>
    <div id="grid-container" class="tabela">
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
    <button class="btn-primary w-100" onclick="goToHome()"><h2>Voltar</h2></button>
</div>
</body>
</html>
