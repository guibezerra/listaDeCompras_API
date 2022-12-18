package br.com.listaDeComprasApi.api.assembler;

import br.com.listaDeComprasApi.api.assembler.generic.ObjectInputDisassembler;
import br.com.listaDeComprasApi.api.model.input.ListaDeComprasInput;
import br.com.listaDeComprasApi.domain.model.ListaDeCompras;
import org.springframework.stereotype.Component;

@Component
public class ListaDeComprasInputDisassembler extends ObjectInputDisassembler<ListaDeComprasInput, ListaDeCompras> {
}
