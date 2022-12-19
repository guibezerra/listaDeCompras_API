package br.com.listaDeComprasApi.api.model.input;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProdutoInput {

    @NotBlank
    private String nome;

    private int quantidade;

    @Valid
    @NotNull
    private ListaDeComprasInputId listaDeCompras;
}
