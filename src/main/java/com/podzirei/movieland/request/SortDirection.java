package com.podzirei.movieland.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum SortDirection {
    ASC("asc"),
    DESC("desc");

    private String value;

    SortDirection(String value) {
        this.value = value;
    }

    @JsonValue
    public String getName() {
        return value;
    }

    @JsonCreator
    public static SortDirection valueFrom(String value) {
        if (null == value) {
            return null;
        }

        for (SortDirection item : SortDirection.values()) {
            if (value.equals(item.getName())) {
                return item;
            }
        }

        throw new IllegalArgumentException(
                "Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
    }

}
