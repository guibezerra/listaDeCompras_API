package br.com.listaDeComprasApi.domain.repository;

import br.com.listaDeComprasApi.domain.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("from Produto p where p.listaDeCompras.idListaDeCompras = :idListaDeCompras")
    List<Produto> findProdutoByIdListaDeCompras(@Param("idListaDeCompras") Long idListaDeCompras);
}
