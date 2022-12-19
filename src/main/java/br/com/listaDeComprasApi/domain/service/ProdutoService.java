package br.com.listaDeComprasApi.domain.service;

import br.com.listaDeComprasApi.domain.exception.EntidadeNaoEncontradaException;
import br.com.listaDeComprasApi.domain.exception.NegocioException;
import br.com.listaDeComprasApi.domain.model.ListaDeCompras;
import br.com.listaDeComprasApi.domain.model.Produto;
import br.com.listaDeComprasApi.domain.repository.ListaDeComprasRepository;
import br.com.listaDeComprasApi.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    ListaDeComprasService listaDeComprasService;

    private static final String MSG_PRODUTO_NAO_ENCOTRADO = "Não existe registro de produto com o id informado.";

    private static final String MSG_PRODUTO_COM_MESMO_NOME = "Já existe registro de produto com o nome inserido.";

    @Transactional
    public Produto buscarPorId (Long idProduto) {
        return produtoRepository.findById(idProduto).orElseThrow(() -> new EntidadeNaoEncontradaException(MSG_PRODUTO_NAO_ENCOTRADO));

    }

    @Transactional
    public Page<Produto> buscarProdutos (Pageable pageable, Long idListaDeCompras) {
        return produtoRepository.findAllProdutosByIdListaDeCompras(pageable, idListaDeCompras);

    }

    @Transactional
    public Produto salvar (Produto produto){
        ListaDeCompras listaDeCompras = listaDeComprasService.buscarPorId(produto.getListaDeCompras().getIdListaDeCompras());

        produto.setListaDeCompras(listaDeCompras);

        verificarSeExisteProdutoComMesmoNome(produto);

        return produtoRepository.save(produto);
    }

    private void verificarSeExisteProdutoComMesmoNome(Produto produto) {
        Produto resultadoBusca;

        if (!Objects.isNull(produto.getIdProduto())){
            resultadoBusca = produtoRepository.findByNameAndIdProdutoAndIdLista(produto.getNome(), produto.getListaDeCompras().getIdListaDeCompras(), produto.getIdProduto());

        } else {
            resultadoBusca = produtoRepository.findByNameAndIdLista(produto.getNome(), produto.getListaDeCompras().getIdListaDeCompras());

        }

        if(!Objects.isNull(resultadoBusca)) {
            throw new NegocioException(MSG_PRODUTO_COM_MESMO_NOME);

        }
    }

    @Transactional
    public void remover(Long idProduto) {
        try {
            produtoRepository.deleteById(idProduto);
            produtoRepository.flush();

        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(MSG_PRODUTO_NAO_ENCOTRADO);

        }
    }

}
