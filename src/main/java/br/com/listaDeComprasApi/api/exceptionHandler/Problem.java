package br.com.listaDeComprasApi.api.exceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class Problem {
    private Integer status;
    private LocalDateTime timestamp;
    private String title;
    private List<Field> fieldList;

    public Problem(Integer status, LocalDateTime timestamp, String title) {
        this.status = status;
        this.timestamp = timestamp;
        this.title = title;
    }

    public Problem(Integer status, LocalDateTime timestamp, String title, List<Field> fieldList) {
        this(status, timestamp, title);
        this.fieldList = fieldList;
    }




}
