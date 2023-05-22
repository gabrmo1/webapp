package br.com.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Procedimento {

    private Integer id;
    private String nroProcedimento;
    private Integer idade;
    private Genero genero;
    private Boolean permitido;
}
