package com.example.projectironplate.database.habit

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.projectironplate.database.DBHelper
import com.example.projectironplate.database.habitGoals.HabitType

class HabitsDAO(private val dbHelper: DBHelper) {
    fun saveHabit(habit: HabitType, value: Double, date: String) {
        val db = dbHelper.getWritableDatabase()
        val values = ContentValues()
        values.put("habit", habit.toString())
        values.put("value", value)
        values.put("date", date.toString())

        db.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE)
        db.close()
    }

    fun getLastSevenDays(habit: HabitType): Habits? {
        val db = dbHelper.getReadableDatabase()
        var cursor: Cursor? = null
        var habits: Habits? = null

        try {
            cursor = db.rawQuery(
                "SELECT * FROM " + TABLE_NAME + " WHERE habit = ? AND date >= date('now', '-6 days') AND date <= date('now')",
                arrayOf<String>(habit.toString())
            )
            if (cursor.moveToFirst()) {
                habits = cursorToHabits(cursor)
            }
        } catch (e: Exception) {
            Log.e(TAG, "failed to get last 7 days of habit")
        } finally {
            if (cursor != null) cursor.close()
            db.close()
        }

        return habits
    }

    private fun cursorToHabits(cursor: Cursor): Habits {
        return Habits(
            cursor.getInt(cursor.getColumnIndexOrThrow("id")),
            cursor.getString(cursor.getColumnIndexOrThrow("habit")),
            cursor.getDouble(cursor.getColumnIndexOrThrow("value")),
            cursor.getString(cursor.getColumnIndexOrThrow("date"))
        )
    }

    companion object {
        private const val TAG = "HabitsDAO"
        private const val TABLE_NAME = "Habits"

        // sql statements
        val SQL_CREATE_TABLE: String = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "habit TEXT NOT NULL, " +
                "value REAL NOT NULL, " +
                "date TEXT NOT NULL," +
                "UNIQUE(habit, date) ON CONFLICT REPLACE" +
                ");"

        val SQL_DROP_HABITS_TABLE: String = "DROP TABLE IF EXISTS " + TABLE_NAME
    }
}
