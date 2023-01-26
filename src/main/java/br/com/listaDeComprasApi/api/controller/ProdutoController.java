package br.com.listaDeComprasApi.api.controller;

import br.com.listaDeComprasApi.api.assembler.ProdutoInputDisassembler;
import br.com.listaDeComprasApi.api.assembler.ProdutoModelAssembler;
import br.com.listaDeComprasApi.api.model.ProdutoModel;
import br.com.listaDeComprasApi.api.model.input.ProdutoInput;
import br.com.listaDeComprasApi.domain.exception.EntidadeNaoEncontradaException;
import br.com.listaDeComprasApi.domain.exception.NegocioException;
import br.com.listaDeComprasApi.domain.model.Produto;
import br.com.listaDeComprasApi.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @Autowired
    ProdutoInputDisassembler produtoInputDisassembler;

    @Autowired
    ProdutoModelAssembler produtoModelAssembler;

    @GetMapping("/{idProduto}")
    public ProdutoModel buscarPorId (@PathVariable Long idProduto) {
        Produto produto = produtoService.buscarPorId(idProduto);

        return produtoModelAssembler.toModel(produto);
    }

    @GetMapping
    public Page<ProdutoModel> buscarProdutos (Long idListaDeCompras, @PageableDefault(size = 10) Pageable pageable ) {
        Page<Produto> produtosPage = produtoService.buscarProdutos(pageable, idListaDeCompras);

        return listaDeProdutosPaginada(pageable, produtosPage);
    }

    private Page<ProdutoModel> listaDeProdutosPaginada (Pageable pageable, Page produtosPage) {
        return new PageImpl<>(
                produtoModelAssembler.toCollectionModel(produtosPage.getContent()),
                pageable,
                produtosPage.getTotalElements()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoModel salvarProduto(@Valid @RequestBody ProdutoInput produtoInput) {
        Produto produto = produtoInputDisassembler.toDomainObject(produtoInput);

        produto = produtoService.salvar(produto);

        return produtoModelAssembler.toModel(produto);
    }

    @PutMapping("/{idProduto}")
    public ProdutoModel atualizarProduto(@PathVariable Long idProduto, @Valid @RequestBody ProdutoInput produtoInput) {
        try {
            Produto produtoAtual = produtoService.buscarPorId(idProduto);

            produtoInputDisassembler.copyToDomainObject(produtoInput, produtoAtual);

            produtoAtual.setIdProduto(idProduto);

            produtoAtual = produtoService.salvar(produtoAtual);

            return produtoModelAssembler.toModel(produtoAtual);

        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @DeleteMapping("/{idProduto}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void removerListaDeCompras(@PathVariable Long idProduto){produtoService.remover(idProduto);}

}
