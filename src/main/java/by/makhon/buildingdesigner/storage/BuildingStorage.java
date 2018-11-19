package by.makhon.buildingdesigner.storage;

import by.makhon.buildingdesigner.bean.Building;

import java.util.List;

/**
 * Building Storage stores whole information about buildings in building list
 * Based on Singleton Pattern
 */
public interface BuildingStorage {
    static BuildingStorage getInstance() {
        return BuildingStorageImpl.getInstance();
    }

    List<Building> getBuildingStorage();
}
