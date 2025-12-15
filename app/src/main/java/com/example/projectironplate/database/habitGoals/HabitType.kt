package com.example.projectironplate.database.habitGoals

enum class HabitType(dbValue: String, unit: String) {
    BODYWEIGHT("bodyweight", "kg"),
    STEPS("steps", "steps"),
    WATER("water", "L"),
    SLEEP("sleep", "hours"),
    CALORIES("calories", "kcal"),
    PROTEIN("protein", "g"),
    CARBS("carbs", "g"),
    FAT("fat", "g");


    private val dbValue: String?
    private val unit: String?

    init {
        this.dbValue = dbValue
        this.unit = unit
    }

    fun getDbValue(): String? {
        return dbValue
    }

    fun getUnit(): String? {
        return unit
    }

    companion object {
        @JvmStatic
        fun fromDbValue(dbValue: String?): HabitType {
            for (type in entries) {
                if (type.getDbValue() == dbValue) {
                    return type
                }
            }
            throw IllegalArgumentException("Unknown habit type: " + dbValue)
        }
    }
}
