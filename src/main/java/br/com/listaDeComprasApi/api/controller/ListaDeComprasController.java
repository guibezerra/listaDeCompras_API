package br.com.listaDeComprasApi.api.controller;

import br.com.listaDeComprasApi.api.assembler.ListaDeComprasInputDisassembler;
import br.com.listaDeComprasApi.api.assembler.ListaDeComprasModelAssembler;
import br.com.listaDeComprasApi.api.model.ListaDeComprasModel;
import br.com.listaDeComprasApi.api.model.input.ListaDeComprasInput;
import br.com.listaDeComprasApi.domain.model.ListaDeCompras;
import br.com.listaDeComprasApi.domain.service.ListaDeComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/listas")
public class ListaDeComprasController {
    @Autowired
    ListaDeComprasService listaDeComprasService;

    @Autowired
    ListaDeComprasModelAssembler listaDeComprasModelAssembler;

    @Autowired
    ListaDeComprasInputDisassembler listaDeComprasInputDisassembler;

    @GetMapping("/{idListaDeCompras}")
    public ResponseEntity<ListaDeComprasModel> buscarPorId (@PathVariable Long idListaDeCompras) {
        ListaDeCompras listaDeCompras = listaDeComprasService.buscarPorId(idListaDeCompras);
        ListaDeComprasModel listaDeComprasModel = listaDeComprasModelAssembler.toModel(listaDeCompras);

        return ResponseEntity.ok(listaDeComprasModel);
    }

    @GetMapping
    public Page<ListaDeComprasModel> buscarTodasAsListasDeCompras (@PageableDefault(size = 10) Pageable pageable ) {
        Page<ListaDeCompras> listaDeComprasPage = listaDeComprasService.buscarTodasListasDeCompras(pageable);

        return listaDeComprasPaginada(pageable, listaDeComprasPage);
    }

    private  Page<ListaDeComprasModel> listaDeComprasPaginada (Pageable pageable, Page<ListaDeCompras> listaDeComprasPage) {
        return new PageImpl<>(
                listaDeComprasModelAssembler.toCollectionModel(listaDeComprasPage.getContent()),
                pageable,
                listaDeComprasPage.getTotalElements()
        );
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ListaDeComprasModel salvarLista(@Valid @RequestBody ListaDeComprasInput listaDeComprasInput) {
        ListaDeCompras listaDeCompras = listaDeComprasInputDisassembler.toDomainObject(listaDeComprasInput);

        listaDeCompras = listaDeComprasService.salvar(listaDeCompras);

        return listaDeComprasModelAssembler.toModel(listaDeCompras);
    }

    @PutMapping("/{idListaDeCompras}")
    public ListaDeComprasModel atualizarLista(@PathVariable Long idListaDeCompras,  @Valid @RequestBody ListaDeComprasInput listaDeComprasInput) {
            ListaDeCompras listaDeComprasAtual = listaDeComprasService.buscarPorId(idListaDeCompras);

            listaDeComprasInputDisassembler.copyToDomainObject(listaDeComprasInput, listaDeComprasAtual);

            listaDeComprasAtual.setIdListaDeCompras(idListaDeCompras);

            listaDeComprasAtual = listaDeComprasService.salvar(listaDeComprasAtual);

            return listaDeComprasModelAssembler.toModel(listaDeComprasAtual);
    }

    @DeleteMapping("/{idListaDeCompras}")
    public ResponseEntity<Void> removerListaDeCompras(@PathVariable Long idListaDeCompras) {
        return listaDeComprasService.remover(idListaDeCompras);
    }
}
