<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            background-image: url("./img/Glass-building-bottom-view-skyscrapers_3840x2160.jpg");
            background-size: 3000px;
            background-repeat: no-repeat;
            background-position: center center;
            padding-top: 100px;
        }

        .container {
            max-width: 400px;
            background-color: #fff;
            border-radius: 10px;
            padding: 20px;
            margin: 0 auto;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .btn-create {
            display: block;
            text-align: center;
            margin-top: 20px;
            font-size: 14px;
            border-radius: 4px;
            padding: 10px 20px;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }

    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function () {
            $('form').submit(function (event) {
                event.preventDefault();

                var procedimento = $('#procedimento').val();
                var idade = $('#idade').val();
                var genero = $('input[name="genero"]:checked').val();

                if (!procedimento || !idade || !genero) {
                    alert('Preencha todos os campos')
                } else {
                    window.location.href = '/web-app/consultar-procedimentos?procedimento=' + procedimento + '&idade=' + idade + '&genero=' + genero;
                }
            });
        });
    </script>
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
        <p class="text-danger position-absolute"><%= request.getAttribute("validation") != null ? request.getAttribute("validation") : "" %></p>
        <a href="/web-app/consultar-procedimentos" class="btn-create">Visualizar procedimentos cadastrados</a>
    </form>
    <a href="/web-app/registrar-procedimentos" class="btn-create">Cadastrar novo procedimento</a>
</div>
</body>
</html>
