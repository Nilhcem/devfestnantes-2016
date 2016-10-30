package com.nilhcem.devfestnantes.data.app.model;

import android.support.annotation.NonNull;

public enum Room {

    NONE(0, ""),
    ROOM_1(1, "Titan"),
    ROOM_2(2, "Belem"),
    ROOM_3(3, "Tour de Bretagne"),
    ROOM_4(4, "Graslin"),
    ROOM_5(5, "Les machines"),
    ROOM_6(6, "Tour LU");

    public final int id;
    public final String label;

    Room(int id, String label) {
        this.id = id;
        this.label = label;
    }

    public static Room getFromId(int id) {
        for (Room room : Room.values()) {
            if (room.id == id) {
                return room;
            }
        }
        return NONE;
    }

    public static Room getFromLabel(@NonNull String label) {
        for (Room room : Room.values()) {
            if (label.equals(room.label)) {
                return room;
            }
        }
        return NONE;
    }
}
