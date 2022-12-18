package br.com.listaDeComprasApi.domain.repository;

import br.com.listaDeComprasApi.domain.model.ListaDeCompras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ListaDeComprasRepository extends JpaRepository<ListaDeCompras, Long>{

    @Query("from ListaDeCompras where nome like :nome and idListaDeCompras <> :idListaDeCompras")
    ListaDeCompras findByNameAndId(@Param("nome") String nome, @Param("idListaDeCompras") Long idListaDeCompras);

    @Query("from ListaDeCompras where nome like :nome")
    ListaDeCompras findByName(@Param("nome") String nome);

}
