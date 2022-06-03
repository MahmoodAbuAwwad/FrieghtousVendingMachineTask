package com.assignment.frieghtous.utils.enums;

public enum NoteValueEnum {
    TWENTY_USD(20),FIFTY_USD(50);

    public final double value;

    private NoteValueEnum(double value) {
        this.value = value;
    }
}
