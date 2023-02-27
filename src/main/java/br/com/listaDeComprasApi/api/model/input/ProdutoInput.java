package br.com.listaDeComprasApi.api.model.input;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class ProdutoInput {

    @NotBlank
    @Size(max = 40)
    private String nome;

    @NotNull
    private int quantidade;

    @Valid
    @NotNull
    private ListaDeComprasInputId listaDeCompras;
}
