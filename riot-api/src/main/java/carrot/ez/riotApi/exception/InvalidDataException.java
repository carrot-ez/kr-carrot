package carrot.ez.riotApi.exception;

public class InvalidDataException extends RuntimeException {

    public InvalidDataException(String message) {
        super("[InvalidDataException] " + message);
    }
}
