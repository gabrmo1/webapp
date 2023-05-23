<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/index.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="js/index.js"></script>
    <title>Consulta de Procedimentos</title>
</head>
<body>
<%
%>
<div class="container">
    <h2 class="text-center mb-4">Consulta de Procedimentos</h2>
    <form>
        <div class="form-group">
            <label for="procedimento">Procedimento:</label>
            <input type="number" class="form-control" id="procedimento" placeholder="Digite o procedimento">
        </div>
        <div class="form-group">
            <label for="idade">Idade:</label>
            <input type="number" class="form-control" id="idade" placeholder="Digite a idade">
        </div>
        <div class="form-group">
            <label>Gênero:</label><br>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" id="masculino" name="genero" value="masculino">
                <label class="form-check-label" for="masculino">Masculino</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" id="feminino" name="genero" value="feminino">
                <label class="form-check-label" for="feminino">
                    Feminino
                </label>
            </div>
        </div>
        <hr/>
        <button type="submit" class="btn btn-primary w-100">Consultar</button>
        <p class="text-danger position-absolute"><%= request.getAttribute("validation") != null ? request.getAttribute("validation") : "" %>
        </p>
        <a href="/web-app/consultar-procedimentos" class="btn-create">Visualizar procedimentos cadastrados</a>
    </form>
    <a href="/web-app/registrar-procedimentos" class="btn-create">Cadastrar novo procedimento</a>
</div>
</body>
</html>
