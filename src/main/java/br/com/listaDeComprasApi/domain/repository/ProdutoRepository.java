package br.com.listaDeComprasApi.domain.repository;

import br.com.listaDeComprasApi.domain.model.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("from Produto p where p.listaDeCompras.idListaDeCompras = :idListaDeCompras")
    List<Produto> findAllProdutosByIdListaDeCompras(@Param("idListaDeCompras") Long idListaDeCompras);

    @Query("from Produto p where p.listaDeCompras.idListaDeCompras = :idListaDeCompras")
    Page<Produto> findAllProdutosByIdListaDeCompras(Pageable pageable, @Param("idListaDeCompras") Long idListaDeCompras);

    @Query("from Produto p where p.nome like :nome and p.listaDeCompras.idListaDeCompras = :idListaDeCompras and p.idProduto <> :idProduto")
    Produto findByNameAndIdProdutoAndIdLista(@Param("nome") String nome, @Param("idListaDeCompras") Long idListaDeCompras, @Param("idProduto") Long idProduto);

    @Query("from Produto p where p.nome like :nome and p.listaDeCompras.idListaDeCompras = :idListaDeCompras")
    Produto findByNameAndIdLista(@Param("nome")String nome, @Param("idListaDeCompras") Long idListaDeCompras);
}
