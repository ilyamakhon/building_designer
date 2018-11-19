package by.makhon.buildingdesigner.storage;

import by.makhon.buildingdesigner.bean.Building;
import by.makhon.buildingdesigner.bean.LightingDevice;
import by.makhon.buildingdesigner.bean.Room;
import by.makhon.buildingdesigner.bean.RoomItem;

import java.util.ArrayList;
import java.util.List;

public class BuildingStorageImpl implements BuildingStorage {

    private static BuildingStorageImpl ourInstance = new BuildingStorageImpl();

    private BuildingStorageImpl() {

    }

    public static BuildingStorageImpl getInstance(){
        return ourInstance;
    }

    @Override
    public List<Building> getBuildingStorage() {

        List<Building> buildings = new ArrayList<>();

        Building building1 = new Building("Building 1");
        Building building2 = new Building("Building 2");
        Building building3 = new Building("Building 3");
        //-------------------------BUILDING 1-------------------------//

        Room room411 = buildRoom("Room 411", 3, 100);
        room411.addLightingDevice("Lamp", buildLightingDevice(150));
        room411.addLightingDevice("Chandelier", buildLightingDevice(450));
        room411.addLightingDevice("Light bulb", buildLightingDevice(150));
        room411.addRoomItem("Chair" ,buildRoomItem(0, 5));
        room411.addRoomItem("Table" ,buildRoomItem(0, 100));
        building1.addRoom(room411.getName() ,room411);

        Room room109 = buildRoom("Room 109", 2, 215);
        room109.addLightingDevice("Lamp", buildLightingDevice(250));
        room109.addLightingDevice("Chandelier" ,buildLightingDevice( 395));
        room109.addLightingDevice("Light bulb", buildLightingDevice(100));
        room109.addRoomItem("Sofa", buildRoomItem(6, 9));
        room109.addRoomItem("Chair", buildRoomItem(2, 4));
        building1.addRoom(room109.getName() ,room109);

        //-------------------------BUILDING 2-------------------------//
        Room room314 = buildRoom("Room 314", 2, 74);
        room314.addLightingDevice("Light bulb", buildLightingDevice(150));
        room314.addLightingDevice("Light bulb", buildLightingDevice(250));
        room314.addLightingDevice("Chandelier", buildLightingDevice(550));
        room314.addRoomItem("Shelf", buildRoomItem(4, 7));
        room314.addRoomItem("Cabinet", buildRoomItem(7, 10));
        building2.addRoom(room314.getName() ,room314);

        Room room212 = buildRoom("Room 314", 1, 30);
        room212.addLightingDevice("Chandelier" , buildLightingDevice(350));
        room212.addLightingDevice("Light bulb", buildLightingDevice(250));
        room212.addRoomItem("Sofa", buildRoomItem(4, 6));
        room212.addRoomItem("Chair", buildRoomItem(2, 2));
        building2.addRoom(room212.getName() ,room212);

        //-------------------------BUILDING 3-------------------------//

        //Another variance to work with rooms and items

        building3.addRoom("Room 309" , buildRoom("Room 309", 4, 150));
        building3.getRoomByName("Room 309").addRoomItem("Chair", buildRoomItem(4, 6));
        building3.getRoomByName("Room 309").addLightingDevice("Lamp", buildLightingDevice(300));
        buildings.add(building1);
        buildings.add(building2);
        buildings.add(building3);

        return buildings;
    }

    private Room buildRoom(String roomName, int windowsAmount, double roomArea) {
        return Room.RoomBuilder.create()
                .withRoomName(roomName)
                .withWindowsAmount(windowsAmount)
                .withRoomArea(roomArea)
                .build();
    }

    private RoomItem buildRoomItem(double minRoomItemArea, double maxRoomItemArea) {
        return RoomItem.RoomItemBuilder.create()
                .withMinRoomItemArea(minRoomItemArea)
                .withMaxRoomItemArea(maxRoomItemArea)
                .build();
    }

    private LightingDevice buildLightingDevice(double illuminanceOfLightingDevice) {
        return LightingDevice.LightingDeviceBuilder.create()
                .withIlluminanceOfLightingDevice(illuminanceOfLightingDevice)
                .build();
    }
}
