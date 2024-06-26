package com.indocyber.Phoenix.models;

import lombok.Getter;

@Getter
public enum RoomType {
    RS("Regular Single Bed"),
    RD("Regular Double Bed"),
    RT("Regular Twin Bed"),
    VS("VIP Aingle Bed"),
    VD("VIP Double Bed"),
    VT("VIP Twin Bed");

    private final  String label;

    RoomType(String label) {
        this.label = label;
    }

}
