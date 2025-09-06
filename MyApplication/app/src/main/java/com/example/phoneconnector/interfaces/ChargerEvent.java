package com.example.phoneconnector.interfaces;

public class ChargerEvent {
    private boolean isCharging;

    public ChargerEvent(boolean isCharging) {
        this.isCharging = isCharging;
    }

    public boolean isCharging() {
        return isCharging;
    }
}
