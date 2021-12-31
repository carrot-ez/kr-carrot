package kr.carrot.Spring.exceptionhandle;

import kr.carrot.Spring.dto.com.ComResponseDto;
import kr.carrot.Spring.exception.InvalidDataException;
import kr.carrot.Spring.exception.NotFoundException;
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
