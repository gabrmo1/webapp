<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
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
            padding-top: 98px;
        }

        .container {
            max-width: 400px;
            background-color: #fff;
            border-radius: 10px;
            padding: 20px;
            margin: 0 auto;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
    </style>
    <title>Tela de Cadastro</title>
</head>
<body>
<div class="container">
    <h2 class="text-center mb-4">Cadastro de Procedimentos</h2>
    <form id="cadastroForm">
        <div class="form-group">
            <label for="procedimento">Procedimento:</label>
            <input type="text" class="form-control" id="procedimento" placeholder="Digite o procedimento">
        </div>
        <div class="form-group">
            <label for="idade">Idade:</label>
            <input type="number" class="form-control" id="idade" placeholder="Digite a idade">
        </div>
        <div class="form-group">
            <label>Gênero:</label><br>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="genero" id="masculino" value="masculino">
                <label class="form-check-label" for="masculino">Masculino</label>
            </div>
            <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="genero" id="feminino" value="feminino">
                <label class="form-check-label" for="feminino">Feminino</label>
            </div>
        </div>
        <div class="form-group">
            <label for="permitido">Permitido:</label>
            <select class="form-control" id="permitido">
                <option value="true">Sim</option>
                <option value="false">Não</option>
            </select>
        </div>
        <hr/>
        <button type="submit" class="btn btn-primary w-100">Enviar</button>
        <a class="btn btn-default w-100" href="/">Voltar</a>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script>
    $(document).ready(function() {
        $('#cadastroForm').submit(function(e) {
            e.preventDefault();

            var procedimento = $('#procedimento').val();
            var idade = $('#idade').val();
            var genero = $('input[name="genero"]:checked').val();
            var permitido = $('#permitido').val();

            if (!procedimento || !idade || !genero) {
                alert('Preencha todos os campos')
            } else {
                $.ajax({
                    url: '/registrar-procedimentos',
                    type: 'POST',
                    data: {
                        procedimento: procedimento,
                        idade: idade,
                        genero: genero,
                        permitido: permitido
                    },
                    success: function(response) {
                        window.location.href = '/'
                        alert('Cadastro realizado com sucesso')
                    },
                    error: function(xhr, status, error) {
                        console.log(error);
                    }
                });
            }
        });
    });
</script>
</body>
</html>
