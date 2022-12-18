package br.com.listaDeComprasApi.api.assembler;

import br.com.listaDeComprasApi.api.assembler.generic.ObjectModelAssembler;
import br.com.listaDeComprasApi.api.model.ListaDeComprasModel;
import br.com.listaDeComprasApi.domain.model.ListaDeCompras;
import org.springframework.stereotype.Component;

@Component
public class ListaDeComprasModelAssembler extends ObjectModelAssembler<ListaDeComprasModel, ListaDeCompras> {
}
