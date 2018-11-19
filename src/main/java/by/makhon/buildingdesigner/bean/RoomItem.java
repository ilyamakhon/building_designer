package by.makhon.buildingdesigner.bean;

/**
 * Class RoomItem, bean which contains information about room items
 */
public class RoomItem {

    /**Min item area needed for planning ranges of item taken area
     * if user don't know how much area room item or furniture will take*/
    private double minArea;
    /**Max item area needed for planning ranges of item taken area
     * if user don't know how much area room item or furniture will take*/
    private double maxArea;

    public double getMinArea() {
        return minArea;
    }

    public void setMinArea(double minArea) {
        this.minArea = minArea;
    }

    public double getMaxArea() {
        return maxArea;
    }

    public void setMaxArea(double maxArea) {
        this.maxArea = maxArea;
    }

    /**
     * Class RoomItemBuilder needed for convenient creating items inside room
     * Based on Builder Pattern
     */
    public static class RoomItemBuilder {
        private RoomItem roomItem = new RoomItem();

        public static RoomItemBuilder create() {
            return new RoomItemBuilder();
        }


        public RoomItemBuilder withMinRoomItemArea(double minRoomItemArea) {
            roomItem.minArea = minRoomItemArea;
            return this;
        }

        public RoomItemBuilder withMaxRoomItemArea(double maxRoomItemArea) {
            roomItem.maxArea = maxRoomItemArea;
            return this;
        }

        public RoomItem build() {
            return roomItem;
        }
    }
}
