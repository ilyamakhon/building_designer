package by.makhon.buildingdesigner;

import by.makhon.buildingdesigner.bean.Building;
import by.makhon.buildingdesigner.storage.BuildingStorage;
import by.makhon.buildingdesigner.validator.Validator;

import java.util.List;

public class Main {
    private static BuildingStorage buildingStorage = BuildingStorage.getInstance();
    private static Validator validator = Validator.getInstance();
    private static List<Building> buildings = buildingStorage.getBuildingStorage();

    public static void main(String[] args) {
        for (Building building : buildings) {
            building.describe();
        }
        validator.validate(buildings);
    }
}