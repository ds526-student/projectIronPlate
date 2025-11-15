package com.example.projectironplate.database.habit;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projectironplate.database.DBHelper;
import com.example.projectironplate.database.habitGoals.HabitType;

import java.util.Date;

public class HabitsDAO {
    private static final String TAG = "HabitsDAO";
    private static final String TABLE_NAME = "Habits";

    // sql statements
    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "habit TEXT NOT NULL, " +
                    "value REAL NOT NULL, " +
                    "date TEXT NOT NULL," +
                    "UNIQUE(habit, date) ON CONFLICT REPLACE" +
                    ");";

    public static final String SQL_DROP_HABITS_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    private final DBHelper dbHelper;

    public HabitsDAO(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void saveHabit(HabitType habit, double value, Date date) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("habit", habit.toString());
        values.put("value", value);
        values.put("date", date.toString());

        db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public Habits getLastSevenDays(HabitType habit, Date currentDate) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        Habits habits = null;

        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE habit = ? AND date >= date('now', '-6 days') AND date <= date('now')", new String[]{habit.toString()});
            if (cursor.moveToFirst()) {
                habits = cursorToHabits(cursor);
            }
        } catch (Exception e) {
            Log.e(TAG, "failed to get last 7 days of habit");
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return habits;
    }

    private Habits cursorToHabits(Cursor cursor) {
        return new Habits(
            cursor.getInt(cursor.getColumnIndexOrThrow("id")),
            cursor.getString(cursor.getColumnIndexOrThrow("habit")),
            cursor.getDouble(cursor.getColumnIndexOrThrow("value")),
            cursor.getString(cursor.getColumnIndexOrThrow("date"))
        );
    }
}
