package com.indocyber.Phoenix.models;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum ReservationMethod {
    OW("Official Web"),
    OS("On Site"),
    AW("Travel Agent Web");
    private final String label;

    ReservationMethod(String label) {
        this.label = label;
    }
}
