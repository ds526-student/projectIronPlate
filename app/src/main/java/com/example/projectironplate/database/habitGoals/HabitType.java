package com.example.projectironplate.database.habitGoals;

public enum HabitType {
    BODYWEIGHT("bodyweight", "kg"),
    STEPS("steps", "steps"),
    WATER("water", "L"),
    SLEEP("sleep", "hours"),
    CALORIES("calories", "kcal"),
    PROTEIN("protein", "g"),
    CARBS("carbs", "g"),
    FAT("fat", "g");


    private final String dbValue;
    private final String unit;

    HabitType(String dbValue, String unit) {
        this.dbValue = dbValue;
        this.unit = unit;
    }

    public String getDbValue() {
        return dbValue;
    }

    public String getUnit() {
        return unit;
    }

    public static HabitType fromDbValue(String dbValue) {
        for (HabitType type : values()) {
            if (type.getDbValue().equals(dbValue)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown habit type: " + dbValue);
    }
}
