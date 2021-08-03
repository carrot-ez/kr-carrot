package kr.carrot.Spring.exception;

public class InvalidDataException extends RuntimeException {

    public InvalidDataException(String message) {
        super("[InvalidDataException] " + message);
    }
}
