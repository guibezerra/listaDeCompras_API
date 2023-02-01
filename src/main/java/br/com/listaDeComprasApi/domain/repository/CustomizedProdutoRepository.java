package br.com.listaDeComprasApi.domain.repository;

import org.springframework.data.repository.query.Param;

public interface CustomizedProdutoRepository {

    Boolean findByNameAndIdProdutoAndIdLista(@Param("nome") String nome, @Param("idListaDeCompras") Long idListaDeCompras, @Param("idProduto") Long idProduto);

    Boolean findByNameAndIdLista(@Param("nome")String nome, @Param("idListaDeCompras") Long idListaDeCompras);
}
