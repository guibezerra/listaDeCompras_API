package br.com.listaDeComprasApi.api.assembler;

import br.com.listaDeComprasApi.api.assembler.generic.ObjectModelAssembler;
import br.com.listaDeComprasApi.api.model.ProdutoModel;
import br.com.listaDeComprasApi.domain.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoModelAssembler extends ObjectModelAssembler<ProdutoModel, Produto> {
}
