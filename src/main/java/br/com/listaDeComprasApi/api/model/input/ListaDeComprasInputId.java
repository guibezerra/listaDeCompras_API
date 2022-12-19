package br.com.listaDeComprasApi.api.model.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ListaDeComprasInputId {

    @NotNull
    Long idListaDeCompras;

}
