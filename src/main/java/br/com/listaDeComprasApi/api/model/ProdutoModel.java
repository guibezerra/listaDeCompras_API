package br.com.listaDeComprasApi.api.model;

import lombok.Data;

@Data
public class ProdutoModel {
    private Long idProduto;

    private String nome;

    private int quantidade;

    private ListaDeComprasModel listaDeCompras;
}
