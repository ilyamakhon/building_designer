package by.makhon.buildingdesigner.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class Room, bean which contains information about room and also contains lighting devices and some room items
 * like furniture or other room items
 */
public class Room {

    private String name;
    private double area;
    private int windowsAmount;
    private HashMap<String ,RoomItem> roomItems = new HashMap<>();
    private HashMap<String, LightingDevice> lightingDevices = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getWindowsAmount() {
        return windowsAmount;
    }

    public void setWindowsAmount(int windowsAmount) {
        this.windowsAmount = windowsAmount;
    }

    public HashMap<String ,RoomItem> getRoomItems() {
        return roomItems;
    }

    public void setRoomItems(HashMap<String ,RoomItem> roomItems) {
        this.roomItems = roomItems;
    }

    public HashMap<String ,LightingDevice> getLightingDevices() {
        return lightingDevices;
    }

    public void setLightingDevices(HashMap<String ,LightingDevice> lightingDevices) {
        this.lightingDevices = lightingDevices;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Double.compare(room.area, area) == 0 &&
                windowsAmount == room.windowsAmount &&
                Objects.equals(name, room.name) &&
                Objects.equals(roomItems, room.roomItems) &&
                Objects.equals(lightingDevices, room.lightingDevices);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, area, windowsAmount, roomItems, lightingDevices);
    }

    public double getSummaryItemArea() {
        double summaryItemArea = 0.0;
        for (Map.Entry<String, RoomItem> roomItem : roomItems.entrySet()) {
            summaryItemArea +=  (roomItem.getValue().getMaxArea() + roomItem.getValue().getMinArea()) / 2;
        }
        return summaryItemArea;
    }

    public double getFreeRoomArea() {
        double freeRoomArea = 0.0;
        for (Map.Entry<String, RoomItem> roomItem : roomItems.entrySet()) {
            freeRoomArea += (roomItem.getValue().getMaxArea() + roomItem.getValue().getMinArea()) / 2;
        }
        return area - freeRoomArea;
    }

    public double getSummaryIlluminance() {
        double summaryIlluminanceOfLightingDevices = 0.0;
        double summaryIlluminanceOfWindows = 0.0;
        for (Map.Entry<String, LightingDevice> lightingDevice : lightingDevices.entrySet()) {
            summaryIlluminanceOfLightingDevices += lightingDevice.getValue().getIlluminance();
        }

        if ( windowsAmount > 0 ) {
           summaryIlluminanceOfWindows = windowsAmount * 700;
        }

        return summaryIlluminanceOfLightingDevices + summaryIlluminanceOfWindows;
    }

    public double getPercentageOfUsingArea() {
        return Math.round(getSummaryItemArea()*100/ area);
    }

    public double getPercentageOfFreeArea() {
        return Math.round(getFreeRoomArea()*100/ area);
    }

    public void addRoomItem(String roomItemName ,RoomItem roomItem) {
        roomItems.put(roomItemName ,roomItem);
    }

    public void addLightingDevice(String lightingDeviceName ,LightingDevice lightingDevice) {
        lightingDevices.put(lightingDeviceName ,lightingDevice);
    }

    public void describe() {
        System.out.println("\n\t" + getName() + "\n" + "\n\t\tRoom area = " + getArea() + " m2");
        System.out.println("\t\tFree room area = " + getFreeRoomArea() + " m2. Or " + getPercentageOfFreeArea() + "% of free space");

        if (roomItems.isEmpty()) {
            System.out.println("\t\tFurniture: there are no furniture in this room");
        } else {
            System.out.println("\t\tFurniture: ");
            for (Map.Entry<String, RoomItem> roomItem : roomItems.entrySet()) {
                if (roomItem.getValue().getMinArea() < 0.0) {
                    throw new IllegalArgumentException("\nRoom " + name +
                            "\nIncorrect minimum room item area " + roomItem.getValue().getMinArea() +
                            "\nRoom item: " + roomItem.getKey());
                }
                if (roomItem.getValue().getMaxArea() < 0.0) {
                    throw new IllegalArgumentException("\nRoom " + name +
                            "\nIncorrect maximum room item area " + roomItem.getValue().getMaxArea()+
                            "\nRoom item: " + roomItem.getKey());
                }
                if (roomItem.getValue().getMaxArea() < roomItem.getValue().getMinArea()) {
                    throw new IllegalArgumentException("\nRoom: " + name +
                            "\nRoom item: " + roomItem.getKey() + " minimum area = " + roomItem.getValue().getMinArea() +
                            " it is greater than maximum area = " + roomItem.getValue().getMaxArea());
                }
                if (roomItem.getValue().getMinArea() == 0.0){
                    System.out.println("\t\t\t" + roomItem.getKey() + " (area = " +
                            roomItem.getValue().getMaxArea() + " m2)");
                } else {
                    System.out.println("\t\t\t" + roomItem.getKey() + " (area = " + "from " +
                            roomItem.getValue().getMinArea() + " to " + roomItem.getValue().getMaxArea() + " m2)");
                }
            }
        }

        System.out.println("\t\tIlluminance = " + getSummaryIlluminance() + " lux:");
        if (windowsAmount > 1) {
            System.out.println("\t\t\t" + windowsAmount + " windows with illuminance of 700.0 lux for each window");
        } else if (windowsAmount == 1) {
            System.out.println("\t\t\t" + windowsAmount + " window with illuminance of 700.0");
        } else if (windowsAmount < 0) {
            System.out.println("\t\t\tIncorrect amount of windows: " + windowsAmount);
        }

        if (lightingDevices.isEmpty()) {
            System.out.println("\t\t\tIn this room no lighting devices.");
        } else {
            for (Map.Entry<String, LightingDevice> lightingDevice : lightingDevices.entrySet()) {
                System.out.println("\t\t\t" + lightingDevice.getKey() + " with illuminance of " +
                        lightingDevice.getValue().getIlluminance() + " lux");
            }
        }
    }

    /**
     * Class RoomBuilder needed for convenient creating rooms inside building
     * Based on Builder Pattern
     */
    public static class RoomBuilder {
        private Room room = new Room();

        public static RoomBuilder create() {
            return new RoomBuilder();
        }

        public RoomBuilder withRoomName(String roomName) {
            room.name = roomName;
            return this;
        }

        public RoomBuilder withRoomArea(double roomArea) {
            room.area = roomArea;
            return this;
        }

        public RoomBuilder withWindowsAmount(int windowsAmount) {
            room.windowsAmount = windowsAmount;
            return this;
        }

        public RoomBuilder withRoomItems(HashMap roomItems) {
            room.roomItems = roomItems;
            return this;
        }

        public RoomBuilder withLightingDevices(HashMap lightingDevices) {
            room.lightingDevices = lightingDevices;
            return this;
        }

        public Room build() {
            return room;
        }
    }
}
