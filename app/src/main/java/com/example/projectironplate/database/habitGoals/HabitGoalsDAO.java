package com.example.projectironplate.database.habitGoals;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projectironplate.database.DBHelper;

public class HabitGoalsDAO {
    private static final String TAG = "HabitGoalsDAO";
    private static final String TABLE_NAME = "habitGoals";

    // SQL statements
    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "habit TEXT, " +
            "goal REAL" +
            ");";

    public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private final DBHelper dbHelper;

    public HabitGoalsDAO(DBHelper dbhelper) {
        this.dbHelper = dbhelper;
    }


    public static void prePopulateTable(SQLiteDatabase db) {
        insertHabitGoal(db, HabitType.BODYWEIGHT, 75.0);
        insertHabitGoal(db, HabitType.STEPS, 10000.0);
        insertHabitGoal(db, HabitType.WATER, 3.0);
        insertHabitGoal(db, HabitType.SLEEP, 8.0);
        insertHabitGoal(db, HabitType.CALORIES, 2500.0);
        insertHabitGoal(db, HabitType.PROTEIN, 150.0);
        insertHabitGoal(db, HabitType.CARBS, 250.0);
        insertHabitGoal(db, HabitType.FAT, 80.0);
    }


    /**
     * Helper method to insert a single habit goal
     */
    private static void insertHabitGoal(SQLiteDatabase db, HabitType habitType, double goal) {
        ContentValues values = new ContentValues();
        values.put("habit", habitType.getDbValue());
        values.put("goal", goal);
        db.insert(TABLE_NAME, null, values);
    }

    public HabitGoals getByHabit(HabitType habitType) {
        return getByHabitString(habitType.getDbValue());
    }

    public HabitGoals getByHabitString(String Habit) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        HabitGoals habitGoals = null;

        try {
            cursor = db.rawQuery("SELECT rowid, * FROM " + TABLE_NAME + " WHERE habit = ?",
                    new String[]{Habit});

            if (cursor.moveToFirst()) {
                habitGoals = cursorToHabitGoals(cursor);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error getting habit goal " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return habitGoals;
    }

    /**
     * Update a habit goal (if user wants to customize)
     */
    public int updateGoal(String habit, double newGoal) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("goal", newGoal);

        int rows = db.update(TABLE_NAME, values, "habit = ?", new String[]{habit});
        db.close();
        return rows;
    }


    /**
     * Convert cursor to HabitGoals object
     */
    private HabitGoals cursorToHabitGoals(Cursor cursor) {
        return new HabitGoals(
                cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cursor.getString(cursor.getColumnIndexOrThrow("habit")),
                cursor.getDouble(cursor.getColumnIndexOrThrow("goal"))
        );
    }
}

