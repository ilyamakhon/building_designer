package by.makhon.buildingdesigner.exception;

public class SpaceUsageToFewException extends RuntimeException {

    private static final long serialVersionUID = 2040301318663082274L;

    public SpaceUsageToFewException() {

    }

    public SpaceUsageToFewException(String message) {
        super(message);
    }
}
