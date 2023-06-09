package br.com.webapp.service;

import javax.enterprise.inject.Model;
import java.util.HashMap;
import java.util.Map;

@Model
public class ConsultarProcedimentosService {

    public Map<String, String> validarDados(String nroProcedimentoStr, String idadeStr, String generoStr) {
        Map<String, String> validacoes = new HashMap<>();

        if (nroProcedimentoStr != null && nroProcedimentoStr.isEmpty()) {
            validacoes.put("validationProcedimento", "O número do procedimento não pode estar vazio");
        } else {
            if (nroProcedimentoStr.length() > 20) {
                validacoes.put("validationProcedimento", "O procedimento deve ter no máximo 20 caracteres");
            }
        }

        if (idadeStr != null && idadeStr.isEmpty()) {
            validacoes.put("validationIdade", "A idade deve ser preenchida");
        } else {
            if (idadeStr.length() > 3) {
                validacoes.put("validationIdade", "Insira somente até 3 dígitos em idade");
            }
        }

        if (generoStr == null || generoStr.isEmpty()) {
            validacoes.put("validationGenero", "O gênero não pode estar vazio");
        }

        return validacoes;
    }

}
