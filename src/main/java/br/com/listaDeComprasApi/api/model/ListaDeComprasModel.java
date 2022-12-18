package br.com.listaDeComprasApi.api.model;

import lombok.Data;

@Data
public class ListaDeComprasModel {

    private Long idListaDeCompras;

    private String nome;

    private boolean concluida;

}
