package br.com.listaDeComprasApi.api.model.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ListaDeComprasInput {

    @NotBlank
    @Size(max = 40)
    private String nome;

    private boolean concluida;

}
