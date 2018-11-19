package by.makhon.buildingdesigner.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Class Building, main bean which contains building name and rooms
 */
public class Building {

    private String name;
    private HashMap<String, Room> rooms = new HashMap<>();

    public Building(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Room> getRooms() {
        return rooms;
    }

    public void setRooms(HashMap<String, Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return Objects.equals(name, building.name) &&
                Objects.equals(rooms, building.rooms);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rooms);
    }

    public void addRoom(String roomName ,Room room) {
        rooms.put(roomName ,room);
    }

    public Room getRoomByName(String roomName){
        for (Map.Entry<String, Room> room : rooms.entrySet()) {
            if (room.getKey().equals(roomName)) {
                return room.getValue();
            }
        }
        throw new NullPointerException("There is no room with name: " + roomName);
    }

    public void describe() {
        System.out.println("\n" + getName());
        for (Map.Entry<String, Room> room : rooms.entrySet()) {
            room.getValue().describe();
        }
    }
}
