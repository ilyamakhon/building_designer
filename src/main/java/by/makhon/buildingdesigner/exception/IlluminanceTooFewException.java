package by.makhon.buildingdesigner.exception;

public class IlluminanceTooFewException extends RuntimeException {

    private static final long serialVersionUID = 716364710355363078L;

    public IlluminanceTooFewException() {

    }

    public IlluminanceTooFewException(String message) {
        super(message);
    }
}
