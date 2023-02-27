package br.com.listaDeComprasApi.api.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Field {
    private String name;
    private String userMessage;

}