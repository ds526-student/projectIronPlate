package com.example.projectironplate.database.habit

/**
 * a table that stores all of the values for the four habits
 */
class Habits
/**
 * constructor for the Habit class
 * @param id id of habit
 * @param habit name of habit
 * @param value value of habit
 * @param date date of habit
 */(
    private var id: Int,
    private var habit: String?,
    private var value: Double,
    private var date: String?
) {
    /**
     * collection of getters
     */
    fun getId(): Int {
        return id
    }

    fun getHabit(): String? {
        return habit
    }

    fun getValue(): Double {
        return value
    }

    fun getDate(): String? {
        return date
    }

    /**
     * collection of setters
     */
    fun setRowId(id: Int) {
        this.id = id
    }

    fun setHabit(habit: String?) {
        this.habit = habit
    }

    fun setValue(value: Double) {
        this.value = value
    }

    fun setDate(date: String?) {
        this.date = date
    }
}



