package br.com.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Procedimento {

    private Integer id;
    private String nroProcedimento;
    private Integer idade;
    private Genero genero;
    private Boolean permitido;
}
