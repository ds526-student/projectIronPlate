package com.example.projectironplate.database.habitGoals;


/**
 * a tabe containing the goals for the associated habits
 */
public class HabitGoals {
    private int id;
    private HabitType habitType;
    private double goal;

    /**
     * constructor for the HabitGoals class
     * @param id id of habit
     * @param habit name of habit
     * @param goal goal of habit
     */
    public HabitGoals (int id, String habit, double goal) {
        this.id = id;
        this.habitType = HabitType.fromDbValue(habit);
        this.goal = goal;
    }

    /**
     * collection of getters
     */
    public int getId() { return id; }
    public HabitType getHabit() { return habitType; }
    public double getGoal() { return goal; }

    /**
     * collection of setters
     */
    public void setId(int Id) { this.id = Id; }
    public void setGoal(double goal) { this.goal = goal; }

    @Override
    public String toString() {
        return "HabitGoals{" +
                "rowId=" + id +
                "habit=" + habitType +
                "goal=" + goal +
                '}';
    }
}
