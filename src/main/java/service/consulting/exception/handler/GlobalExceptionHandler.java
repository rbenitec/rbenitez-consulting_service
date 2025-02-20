package service.consulting.exception.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;
import service.consulting.dto.ErrorDto;
import service.consulting.exception.ApiRestExternalException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiRestExternalException.class)
    public Mono<ResponseEntity<ErrorDto>> handleCustomException(ApiRestExternalException ex) {
        ErrorDto errorDto = new ErrorDto(
                ex.getStatus().value(),
                ex.getMessage(),
                ex.getDetail(),
                ex.getUrl()
        );
        return Mono.just(ResponseEntity.status(ex.getStatus()).body(errorDto));
    }
}
