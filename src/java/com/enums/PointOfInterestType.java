package com.enums;

public enum PointOfInterestType {
    School("School"),
    Hospital("Hospital"),
    Restaurant("Restuarant"),
    Church("Church"),
    Monument("Monument"),
    Castle("Castle"),
    Coffee_Shop("Coffee_Shop"),
    Recreation("Recreation"),
    Hotel("Hotel");

    private final String label;

    PointOfInterestType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

