package com.as.logitechchallenge.model;

import java.util.ArrayList;

/**
 * Created by suresh on 11-02-2016.
 */
public class DeviceListResponse {
    ArrayList<Devices> devices;

    public ArrayList<Devices> getDevices() {
        return devices;
    }

    public void setDevices(ArrayList<Devices> devices) {
        this.devices = devices;
    }

    public class Devices {
        String deviceType, model, name;

        public String getDeviceType() {
            return deviceType;
        }

        public void setDeviceType(String deviceType) {
            this.deviceType = deviceType;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }
    }
}
