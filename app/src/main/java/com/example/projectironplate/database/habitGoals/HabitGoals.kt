package com.example.projectironplate.database.habitGoals

import com.example.projectironplate.database.habitGoals.HabitType.Companion.fromDbValue


/**
 * a tabe containing the goals for the associated habits
 */
class HabitGoals(private var id: Int, habit: String?, private var goal: Double) {
    private val habitType: HabitType?

    /**
     * constructor for the HabitGoals class
     * @param id id of habit
     * @param habit name of habit
     * @param goal goal of habit
     */
    init {
        this.habitType = fromDbValue(habit)
    }

    /**
     * collection of getters
     */
    fun getId(): Int {
        return id
    }

    fun getHabit(): HabitType? {
        return habitType
    }

    fun getGoal(): Double {
        return goal
    }

    /**
     * collection of setters
     */
    fun setId(Id: Int) {
        this.id = Id
    }

    fun setGoal(goal: Double) {
        this.goal = goal
    }

    override fun toString(): String {
        return "HabitGoals{" +
                "rowId=" + id +
                "habit=" + habitType +
                "goal=" + goal +
                '}'
    }
}
