package br.com.listaDeComprasApi.domain.service;

import br.com.listaDeComprasApi.domain.exception.EntidadeNaoEncontradaException;
import br.com.listaDeComprasApi.domain.exception.NegocioException;
import br.com.listaDeComprasApi.domain.model.ListaDeCompras;
import br.com.listaDeComprasApi.domain.model.Produto;
import br.com.listaDeComprasApi.domain.repository.ListaDeComprasRepository;
import br.com.listaDeComprasApi.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ListaDeComprasService {
    @Autowired
    ListaDeComprasRepository listaDeComprasRepository;
    @Autowired
    ProdutoRepository produtoRepository;
    private static final String MSG_LISTA_NAO_ENCOTRADA = "Não existe registro de lista com o id informado.";
    private static final String MSG_LISTA_COM_MESMO_NOME = "Já existe lista com o nome informado.";

    @Transactional
    public ListaDeCompras buscarPorId (Long idListaDeCompras) {
        return listaDeComprasRepository.findById(idListaDeCompras)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(MSG_LISTA_NAO_ENCOTRADA));
    }

    @Transactional
    public Page<ListaDeCompras> buscarTodasListasDeCompras (Pageable pageable) {
        return listaDeComprasRepository.findAll(pageable);
    }

    @Transactional
    public ListaDeCompras salvar (ListaDeCompras listaDeCompras){
        verificarSeExisteListaComMesmoNome(listaDeCompras);

        return listaDeComprasRepository.save(listaDeCompras);
    }

    private void verificarSeExisteListaComMesmoNome(ListaDeCompras listaDeCompras) {
        ListaDeCompras resultadoBusca;

        if (isIdNaoNulo(listaDeCompras)){
            resultadoBusca = listaDeComprasRepository.findByNameAndId(listaDeCompras.getNome(), listaDeCompras.getIdListaDeCompras());

        } else {
            resultadoBusca = listaDeComprasRepository.findByName(listaDeCompras.getNome());
        }

        if(!Objects.isNull(resultadoBusca)) {
            throw new NegocioException(MSG_LISTA_COM_MESMO_NOME);
        }

    }

    private boolean isIdNaoNulo(ListaDeCompras listaDeCompras) {
        return !Objects.isNull(listaDeCompras.getIdListaDeCompras());
    }

    @Transactional
    public ResponseEntity<Void> remover(Long idListaDeCompras) {
        if (isListaDeComprasInexistente(idListaDeCompras)) {
            throw new EntidadeNaoEncontradaException(MSG_LISTA_NAO_ENCOTRADA);
        }

        removerProdutosDalistaDeCompras(idListaDeCompras);
        removerListaDeCompras(idListaDeCompras);

        return ResponseEntity.noContent().build();
    }

    private boolean isListaDeComprasInexistente(Long idListaDeCompras) {
        return !listaDeComprasRepository.existsById(idListaDeCompras);
    }

    private void removerProdutosDalistaDeCompras (Long idListaDeCompras) {
        List<Produto> produtoList = produtoRepository.findAllProdutosByIdListaDeCompras(idListaDeCompras);

        produtoRepository.deleteAll(produtoList);
        produtoRepository.flush();
    }

    private void removerListaDeCompras (Long idListaDeCompras){
        listaDeComprasRepository.deleteById(idListaDeCompras);
        listaDeComprasRepository.flush();
    }



}
