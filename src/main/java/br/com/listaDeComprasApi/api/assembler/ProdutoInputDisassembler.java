package br.com.listaDeComprasApi.api.assembler;

import br.com.listaDeComprasApi.api.assembler.generic.ObjectInputDisassembler;
import br.com.listaDeComprasApi.api.model.input.ProdutoInput;
import br.com.listaDeComprasApi.domain.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoInputDisassembler extends ObjectInputDisassembler<ProdutoInput, Produto> {
}
