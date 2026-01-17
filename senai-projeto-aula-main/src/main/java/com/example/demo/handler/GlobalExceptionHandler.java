package com.example.demo.handler;

import com.example.demo.exception.ProdutoNaoEncontradoException;
import com.example.demo.model.ErroDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProdutoNaoEncontradoException.class)
    public ResponseEntity<ErroDto> handlerProdutoNaoEncontrado(ProdutoNaoEncontradoException ex){
        System.err.println("Exceçãp de Negócio (404): " + ex.getMessage());

        ErroDto erro = new ErroDto();
        erro.setStatus(HttpStatus.NOT_FOUND.value());
        erro.setErro("Not Found");
        erro.setMensagem(ex.getMessage());

        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroDto> handlerValidationException(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e ->e.getField() + ":" + e.getDefaultMessage())
                .toList();

        ErroDto erro = new ErroDto();
        erro.setStatus(HttpStatus.BAD_REQUEST.value());
        erro.setErro("Bad Request");
        erro.setMensagem("Falha na validação:" +String.join("," , erros));

        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroDto> handlerGenericError(Exception ex){
        ex.printStackTrace();

        ErroDto erro = new ErroDto();
        erro.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        erro.setMensagem("Ocorreu um erro inesperado no sevidor.");

        return new ResponseEntity<>(erro,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
