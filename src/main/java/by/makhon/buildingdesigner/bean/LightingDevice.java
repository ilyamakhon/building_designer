package by.makhon.buildingdesigner.bean;

/**
 * Class LightingDevice, bean which contains whole information about room lighting device
 */
public class LightingDevice {

    private double illuminance;

    public double getIlluminance() {
        return illuminance;
    }

    public void setIlluminance(double illuminance) {
        this.illuminance = illuminance;
    }

    /**
     * Class LightingDeviceBuilder needed for convenient creating lighting devices inside room
     * based on Builder Pattern
     */
    public static class LightingDeviceBuilder {
        private LightingDevice lightingDevice = new LightingDevice();

        public static LightingDeviceBuilder create() {
            return new LightingDeviceBuilder();
        }

        public LightingDeviceBuilder withIlluminanceOfLightingDevice(double illuminanceOfLightingDevice) {
            lightingDevice.illuminance = illuminanceOfLightingDevice;
            return this;
        }

        public LightingDevice build(){
            return lightingDevice;
        }
    }

}
