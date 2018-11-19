package by.makhon.buildingdesigner.exception;

public class SpaceUsageToMuchException extends RuntimeException {

    private static final long serialVersionUID = -7962390591645728632L;

    public SpaceUsageToMuchException() {

    }

    public SpaceUsageToMuchException(String message) {
        super(message);
    }
}
