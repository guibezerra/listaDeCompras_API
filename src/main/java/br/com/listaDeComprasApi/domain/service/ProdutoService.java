package br.com.listaDeComprasApi.domain.service;

import br.com.listaDeComprasApi.domain.exception.EntidadeNaoEncontradaException;
import br.com.listaDeComprasApi.domain.exception.NegocioException;
import br.com.listaDeComprasApi.domain.model.ListaDeCompras;
import br.com.listaDeComprasApi.domain.model.Produto;
import br.com.listaDeComprasApi.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    ListaDeComprasService listaDeComprasService;
    private static final String MSG_PRODUTO_NAO_ENCOTRADO = "Não existe registro de produto com o id informado.";
    private static final String MSG_PRODUTO_COM_MESMO_NOME = "Já existe registro de produto com o nome inserido.";
    private static final String MSG_LISTA_NAO_ENCOTRADA = "Não existe registro de lista com o id informado.";

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
        buscaEAssociaListaDeComprasAProduto(produto);
        verificarSeExisteProdutoComMesmoNome(produto);

        return produtoRepository.save(produto);
    }

    private void buscaEAssociaListaDeComprasAProduto (Produto produto) {
        ListaDeCompras listaDeCompras = listaDeComprasService.buscarPorId(produto.getListaDeCompras().getIdListaDeCompras());

        verificarSeListaRetornadaNaoEVazia(listaDeCompras);

        produto.setListaDeCompras(listaDeCompras);
    }

    private void verificarSeListaRetornadaNaoEVazia(ListaDeCompras listaDeCompras) {
        if (isListaNula(listaDeCompras)) {
            throw new EntidadeNaoEncontradaException(MSG_LISTA_NAO_ENCOTRADA);
        }
    }

    private boolean isListaNula(ListaDeCompras listaDeCompras){
        return Objects.isNull(listaDeCompras);
    }

    private void verificarSeExisteProdutoComMesmoNome(Produto produto) {
        Boolean existeProdutoComMesmoNome;

        if (isIdNaoNulo(produto)){
            existeProdutoComMesmoNome = produtoRepository.findByNameAndIdProdutoAndIdLista(produto.getNome(), produto.getListaDeCompras().getIdListaDeCompras(), produto.getIdProduto());

        } else {
            existeProdutoComMesmoNome = produtoRepository.findByNameAndIdLista(produto.getNome(), produto.getListaDeCompras().getIdListaDeCompras());
        }

        if(existeProdutoComMesmoNome) {
            throw new NegocioException(MSG_PRODUTO_COM_MESMO_NOME);
        }
    }

    private boolean isIdNaoNulo(Produto produto){
        return !Objects.isNull(produto.getIdProduto());
    }

    @Transactional
    public ResponseEntity <Void> remover(Long idProduto) {
        if (isProdutoInexistente(idProduto)) {
            throw new EntidadeNaoEncontradaException(MSG_PRODUTO_NAO_ENCOTRADO);
        }

        produtoRepository.deleteById(idProduto);
        produtoRepository.flush();

        return ResponseEntity.noContent().build();
    }

    private boolean isProdutoInexistente(Long idProduto) {
        return !produtoRepository.existsById(idProduto);
    }

}
