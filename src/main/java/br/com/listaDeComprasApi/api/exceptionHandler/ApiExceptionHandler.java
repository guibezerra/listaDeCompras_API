package br.com.listaDeComprasApi.api.exceptionHandler;

import br.com.listaDeComprasApi.domain.exception.EntidadeNaoEncontradaException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontradaException(
            EntidadeNaoEncontradaException ex, WebRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        List<Problem.Field> fild = new ArrayList<Problem.Field>();

        Problem problem = new Problem(status.value(),LocalDateTime.now(), "Recurso Não Encontrado", fild);

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<Problem.Field> fild = new ArrayList<Problem.Field>();

        ex.getBindingResult().getAllErrors().stream().forEach(objectError ->
                fild.add(new Problem.Field( ((FieldError) objectError).getField(),
                                            objectError.getDefaultMessage())));

        Problem problem = new Problem(status.value(),LocalDateTime.now(), "Um ou mais Campos Inválidos", fild);

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }
}
