package racxa.utils.exceptions;

public class RecordAlreadyExistsException extends Exception{
    public RecordAlreadyExistsException() {
        super();
    }

    public RecordAlreadyExistsException(String message) {
        super(message);
    }
}
