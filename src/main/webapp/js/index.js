$(document).ready(function () {
    $('form').submit(function (event) {
        event.preventDefault();

        const procedimento = $('#procedimento').val();
        const idade = $('#idade').val();
        const genero = $('input[name="genero"]:checked').val();

        if (idade && idade.length > 3) {
            alert("Insira somente até 3 dígitos em idade");
            return;
        }

        if (!procedimento || !idade || !genero) {
            alert('Preencha todos os campos');
        } else {
            window.location.href = '/web-app/consultar-procedimentos?procedimento=' + procedimento + '&idade=' + idade + '&genero=' + genero;
        }
    });
});