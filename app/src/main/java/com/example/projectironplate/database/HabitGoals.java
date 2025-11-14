package com.example.projectironplate.database;


/**
 * a tabe containing the goals for the associated habits
 */
public class HabitGoals {
    private int id;
    private String habit;
    private double goal;

    /**
     * constructor for the HabitGoals class
     * @param id id of habit
     * @param habit name of habit
     * @param goal goal of habit
     */
    public HabitGoals (int id, String habit, double goal) {
        this.id = id;
        this.habit = habit;
        this.goal = goal;
    }

    /**
     * collection of getters
     */
    public int getId() { return id; }
    public String getHabit() { return habit; }
    public double getGoal() { return goal; }

    /**
     * collection of setters
     */
    public void setId(int Id) { this.id = Id; }
    public void setHabit(String habit) { this.habit = habit; }
    public void setGoal(double goal) { this.goal = goal; }
}
