$(document).ready(function () {
    $('#cadastroForm').submit(function (e) {
        e.preventDefault();

        const procedimento = $('#procedimento').val();
        const idade = $('#idade').val();
        const genero = $('input[name="genero"]:checked').val();
        const permitido = $('#permitido').val();

        $.ajax({
            url: '/web-app/registrar-procedimentos',
            type: 'POST',
            data: {
                procedimento: procedimento,
                idade: idade,
                genero: genero,
                permitido: permitido
            },
            success: function (response) {
                window.location.href = '/web-app'
                if (response) {
                    alert(response)
                }
            },
            error: function (error) {
                if (error && error.responseText) {
                    alert(error.responseText);
                }
            }
        });
    });
});