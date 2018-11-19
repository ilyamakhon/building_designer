package by.makhon.buildingdesigner.exception;

public class IlluminanceTooMuchException extends RuntimeException {

    private static final long serialVersionUID = -5731928491715869372L;

    public IlluminanceTooMuchException() {

    }

    public IlluminanceTooMuchException(String message) {
        super(message);
    }
}
