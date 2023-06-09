package br.com.webapp.service;

import br.com.webapp.model.Genero;
import br.com.webapp.model.Procedimento;

import javax.enterprise.inject.Model;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Model
public class RegistrarProcedimentosService {

    public List<String> validarProcedimento(Procedimento procedimento) {
        List<String> validations = new ArrayList<>();

        if (procedimento != null) {
            //-------------------- Validations for nroProcedimento --------------------
            if (procedimento.getNroProcedimento() != null) {
                if (procedimento.getNroProcedimento().length() > 20) {
                    validations.add("O número do procedimento deve conter no máximo 20 catacteres");
                }
            } else {
                validations.add("O número do procedimento não pode estar vazio");
            }

            //-------------------- Validations for idade --------------------
            if (procedimento.getIdade() != null) {
                if (procedimento.getIdade().toString().length() > 3) {
                    validations.add("Insira somente até 3 dígitos em idade");
                }
            } else {
                validations.add("A idade não pode estar vazia");
            }

            //-------------------- Validations for genero --------------------
            if (procedimento.getGenero() == null) {
                validations.add("O Gênero deve ser selecionado");
            }

            //-------------------- Validations for permitido --------------------
            if (procedimento.getPermitido() == null) {
                validations.add("Informe se o procedimento será permitido");
            }
        }

        return validations;
    }

    public Procedimento getProcedimentoObjectFromRequest(HttpServletRequest request) {
        String nroProcedimento = request.getParameter("procedimento");
        Integer idade = Integer.parseInt(request.getParameter("idade"));
        Genero genero = Genero.valueOf(request.getParameter("genero").toUpperCase());
        Boolean permitido = Boolean.valueOf(request.getParameter("permitido"));

        return Procedimento.builder()
                .nroProcedimento(nroProcedimento)
                .idade(idade)
                .genero(genero)
                .permitido(permitido)
                .build();
    }
}
