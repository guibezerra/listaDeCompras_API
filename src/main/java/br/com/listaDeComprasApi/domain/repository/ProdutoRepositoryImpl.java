package br.com.listaDeComprasApi.domain.repository;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class ProdutoRepositoryImpl implements CustomizedProdutoRepository{
    @PersistenceContext
    private EntityManager manager;

    @Override
    public Boolean findByNameAndIdProdutoAndIdLista(String nome, Long idListaDeCompras, Long idProduto) {
       StringBuilder jpql = new StringBuilder();

       jpql.append(" select count(p.idProduto) ");
       jpql.append(" from Produto p ");
       jpql.append(" where p.nome like :nome and p.listaDeCompras.idListaDeCompras = :idListaDeCompras ");
       jpql.append(" and p.idProduto <> :idProduto group by p.idProduto ");

        TypedQuery<Long> query = manager
                .createQuery(jpql.toString(), Long.class);

        query.setParameter("nome", nome);
        query.setParameter("idListaDeCompras", idListaDeCompras);
        query.setParameter("idProduto", idProduto);

        Boolean resultadoVazio = query.getResultList().isEmpty();

        if (resultadoVazio) {
            return false;
        }

        return true;
    }

    @Override
    public Boolean findByNameAndIdLista(String nome, Long idListaDeCompras) {
        StringBuilder jpql = new StringBuilder();

        jpql.append(" select count(p.idProduto) ");
        jpql.append(" from Produto p ");
        jpql.append(" where p.nome like :nome and p.listaDeCompras.idListaDeCompras = :idListaDeCompras ");
        jpql.append(" group by p.idProduto ");

        TypedQuery<Long> query = manager
                .createQuery(jpql.toString(), Long.class);

        query.setParameter("nome", nome);
        query.setParameter("idListaDeCompras", idListaDeCompras);

        Boolean resultadoVazio = query.getResultList().isEmpty();

        if (resultadoVazio) {
            return false;
        }

        return true;
    }
}
