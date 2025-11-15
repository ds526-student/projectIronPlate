package com.example.projectironplate.database.habit;

import java.sql.Date;

/**
 * a table that stores all of the values for the four habits
 */
public class Habits {
    private int rowId;
    private String habit;
    private double value;
    private Date date;


    /**
     * constructor for the Habit class
     * @param rowId id of habit
     * @param habit name of habit
     * @param value value of habit
     * @param date date of habit
     */
    public Habits(int rowId, String habit, double value, Date date) {
        this.rowId = rowId;
        this.habit = habit;
        this.value = value;
        this.date = date;
    }


    /**
     * collection of getters
     */
    public int getRowId() { return rowId; }
    public String getHabit() { return habit; }
    public double getValue() { return value; }
    public Date getDate() { return date; }

    /**
     * collection of setters
     */
    public void setRowId(int rowId) { this.rowId = rowId; }
    public void setHabit(String habit) { this.habit = habit; }
    public void setValue(double value) { this.value = value; }
    public void setDate(Date date) { this.date = date; }
}



