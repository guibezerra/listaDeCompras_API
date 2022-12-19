package br.com.listaDeComprasApi.api.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.management.ConstructorParameters;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Problem {
    private Integer status;
    private LocalDateTime timestamp;
    private String title;
    private List<Field> validations;

    @Getter
    @AllArgsConstructor
    public static class Field {

        private String name;
        private String userMessage;

    }
}
