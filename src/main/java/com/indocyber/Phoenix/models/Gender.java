package com.indocyber.Phoenix.models;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("Male"),
    FEMALE("Female");
    private final String label;

    Gender(String label) {
        this.label = label;
    }
}
