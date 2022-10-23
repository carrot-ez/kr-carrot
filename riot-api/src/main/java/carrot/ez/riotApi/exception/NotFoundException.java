package carrot.ez.riotApi.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super("[NotFoundException] "+message);
    }
}
