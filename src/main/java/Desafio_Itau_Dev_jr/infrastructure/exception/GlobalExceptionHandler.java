package Desafio_Itau_Dev_jr.infrastructure.exception;

import Desafio_Itau_Dev_jr.infrastructure.UnprocessableEntityException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Captura nossa exceção personalizada ( Regra de negócio: valor < 0 ou data futura )
    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<Void> handleUnprocessableEntity(){
        return ResponseEntity.unprocessableEntity().build();
    }


    // Captura erros de sintaxe no JSON ou tipos de dados inválidos ( ex: enviar string no valor )
    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            MethodArgumentTypeMismatchException.class
    })
    public ResponseEntity<Void> handleBadRequest(){
        return ResponseEntity.badRequest().build();
    }


    // Capturar qualquer outro erro inesperado ( Generic 400 como fallback de segurança )
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Void> handleGeneralExceptio(){
        return ResponseEntity.badRequest().build();
    }

}
