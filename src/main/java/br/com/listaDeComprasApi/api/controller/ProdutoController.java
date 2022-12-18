package br.com.listaDeComprasApi.api.controller;

import br.com.listaDeComprasApi.api.assembler.ProdutoInputDisassembler;
import br.com.listaDeComprasApi.api.assembler.ProdutoModelAssembler;
import br.com.listaDeComprasApi.api.model.ListaDeComprasModel;
import br.com.listaDeComprasApi.api.model.ProdutoModel;
import br.com.listaDeComprasApi.domain.model.ListaDeCompras;
import br.com.listaDeComprasApi.domain.model.Produto;
import br.com.listaDeComprasApi.domain.repository.ProdutoRepository;
import br.com.listaDeComprasApi.domain.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Page<ListaDeComprasModel> buscarTodasAsListasDeCompras (@PageableDefault(size = 10) Pageable pageable ) {
        Page<ListaDeCompras> listaDeComprasPage = listaDeComprasService.buscarTodasListasDeCompras(pageable);

        Page<ListaDeComprasModel> listaDeComprasModelsPage = new PageImpl<>(
                listaDeComprasModelAssembler.toCollectionModel(listaDeComprasPage.getContent()),
                pageable,
                listaDeComprasPage.getTotalElements()
        );

        return listaDeComprasModelsPage;
    }

}
