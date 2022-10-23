package carrot.ez.riotApi.exceptionhandle;

import carrot.ez.riotApi.dto.com.ComResponseDto;
import carrot.ez.riotApi.exception.InvalidDataException;
import carrot.ez.riotApi.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ComResponseDto<?> handleNotFoundException(NotFoundException e) {
        return ComResponseDto.fail(e.getMessage());
    }

    @ExceptionHandler(InvalidDataException.class)
    public ComResponseDto<?> handleInvalidDataException(InvalidDataException e) {
        return ComResponseDto.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ComResponseDto<?> handleException(Exception e) {
        return ComResponseDto.fail(e.getMessage());
    }

}
