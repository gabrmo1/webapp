$(document).ready(function() {
    $('#cadastroForm').submit(function(e) {
        e.preventDefault();

        const procedimento = $('#procedimento').val();
        const idade = $('#idade').val();
        const genero = $('input[name="genero"]:checked').val();
        const permitido = $('#permitido').val();

        if (idade && idade.length > 3) {
            alert("Insira somente até 3 dígitos em idade");
            return;
        }

        if (!procedimento || !idade || !genero) {
            alert('Preencha todos os campos')
        } else {
            $.ajax({
                url: '/web-app/registrar-procedimentos',
                type: 'POST',
                data: {
                    procedimento: procedimento,
                    idade: idade,
                    genero: genero,
                    permitido: permitido
                },
                success: function(response) {
                    window.location.href = '/web-app'
                    alert('Cadastro realizado com sucesso')
                },
                error: function(xhr, status, error) {
                    console.log(error);
                }
            });
        }
    });
});