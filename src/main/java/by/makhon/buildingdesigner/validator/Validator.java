package by.makhon.buildingdesigner.validator;

import by.makhon.buildingdesigner.bean.Building;
import by.makhon.buildingdesigner.exception.IlluminanceTooFewException;
import by.makhon.buildingdesigner.exception.IlluminanceTooMuchException;
import by.makhon.buildingdesigner.exception.SpaceUsageToFewException;
import by.makhon.buildingdesigner.exception.SpaceUsageToMuchException;

import java.util.List;

/**
 * Validator provides quality of creating items according to agreed parameters
 * Based on Singleton Pattern
 */
public interface Validator {
    static Validator getInstance() {
        return ValidatorImpl.getInstance();
    }

    void validate(List<Building> buildings) throws IlluminanceTooFewException, IlluminanceTooMuchException,
            SpaceUsageToFewException, SpaceUsageToMuchException;
}
