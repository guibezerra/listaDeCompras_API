package br.com.listaDeComprasApi.api.model;

import br.com.listaDeComprasApi.domain.model.ListaDeCompras;
import lombok.Data;

@Data
public class ProdutoModel {
    private Long idProduto;

    private String nome;

    private int quantidade;

    private ListaDeCompras listaDeCompras;
}
