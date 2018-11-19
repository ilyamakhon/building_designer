package by.makhon.buildingdesigner.validator;

import by.makhon.buildingdesigner.bean.Building;
import by.makhon.buildingdesigner.bean.Room;
import by.makhon.buildingdesigner.exception.*;

import java.util.List;
import java.util.Map;

public class ValidatorImpl implements Validator {
    private static ValidatorImpl ourInstance = new ValidatorImpl();

    private ValidatorImpl() {
    }

    public static ValidatorImpl getInstance() {
        return ourInstance;
    }

    @Override
    public void validate(List<Building> buildings) {
        for (Building building : buildings) {
            for (Map.Entry<String, Room> room : building.getRooms().entrySet()) {
                if (room.getValue().getArea() < 1.0) {
                    throw new SpaceUsageToFewException("\nRoom: " + room.getKey() + " area = " + room.getValue().getArea() +
                            " it should be greater than 1 m2");
                }
                if ( room.getValue().getSummaryItemArea() > (room.getValue().getArea() * 0.7) ) {
                    throw new SpaceUsageToMuchException("\nRoom: " + room.getKey() + " items summary area is " + room.getValue().getSummaryItemArea() +
                            " or " + room.getValue().getPercentageOfUsingArea() + "%." + " It is greater than 70% of whole room area which is equal to " + room.getValue().getArea());
                }
                if (room.getValue().getSummaryIlluminance() < 300) {
                    throw new IlluminanceTooFewException("\nRoom: " + room.getKey() + " summary illuminance is "
                            + room.getValue().getSummaryIlluminance() + " lux. It must be greater than 300 lux");
                }
                if (room.getValue().getSummaryIlluminance() > 4000) {
                    throw new IlluminanceTooMuchException("\nRoom: " + room.getKey() + " summary illuminance is "
                            + room.getValue().getSummaryIlluminance() + " lux. It must be less than 4000 lux");
                }
            }
        }
    }
}
