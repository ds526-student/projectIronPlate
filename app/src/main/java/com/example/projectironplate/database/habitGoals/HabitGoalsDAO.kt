package com.example.projectironplate.database.habitGoals

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.example.projectironplate.database.DBHelper

class HabitGoalsDAO(private val dbHelper: DBHelper) {
    fun getByHabit(habitType: HabitType): HabitGoals? {
        return getByHabitString(habitType.getDbValue())
    }

    fun getByHabitString(Habit: String?): HabitGoals? {
        val db = dbHelper.getReadableDatabase()
        var cursor: Cursor? = null
        var habitGoals: HabitGoals? = null

        try {
            cursor = db.rawQuery(
                "SELECT rowid, * FROM " + TABLE_NAME + " WHERE habit = ?",
                arrayOf<String?>(Habit)
            )

            if (cursor.moveToFirst()) {
                habitGoals = cursorToHabitGoals(cursor)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error getting habit goal " + e.message)
        } finally {
            if (cursor != null) cursor.close()
            db.close()
        }

        return habitGoals
    }

    /**
     * Update a habit goal (if user wants to customize)
     */
    fun updateGoal(habit: String?, newGoal: Double): Int {
        val db = dbHelper.getWritableDatabase()
        val values = ContentValues()
        values.put("goal", newGoal)

        val rows = db.update(TABLE_NAME, values, "habit = ?", arrayOf<String?>(habit))
        db.close()
        return rows
    }


    /**
     * Convert cursor to HabitGoals object
     */
    private fun cursorToHabitGoals(cursor: Cursor): HabitGoals {
        return HabitGoals(
            cursor.getInt(cursor.getColumnIndexOrThrow("id")),
            cursor.getString(cursor.getColumnIndexOrThrow("habit")),
            cursor.getDouble(cursor.getColumnIndexOrThrow("goal"))
        )
    }

    companion object {
        private const val TAG = "HabitGoalsDAO"
        private const val TABLE_NAME = "habitGoals"

        // SQL statements
        val SQL_CREATE_TABLE: String = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "habit TEXT, " +
                "goal REAL" +
                ");"

        val SQL_DROP_TABLE: String = "DROP TABLE IF EXISTS " + TABLE_NAME

        fun prePopulateTable(db: SQLiteDatabase) {
            insertHabitGoal(db, HabitType.BODYWEIGHT, 75.0)
            insertHabitGoal(db, HabitType.STEPS, 10000.0)
            insertHabitGoal(db, HabitType.WATER, 3.0)
            insertHabitGoal(db, HabitType.SLEEP, 8.0)
            insertHabitGoal(db, HabitType.CALORIES, 2500.0)
            insertHabitGoal(db, HabitType.PROTEIN, 150.0)
            insertHabitGoal(db, HabitType.CARBS, 250.0)
            insertHabitGoal(db, HabitType.FAT, 80.0)
        }


        /**
         * Helper method to insert a single habit goal
         */
        private fun insertHabitGoal(db: SQLiteDatabase, habitType: HabitType, goal: Double) {
            val values = ContentValues()
            values.put("habit", habitType.getDbValue())
            values.put("goal", goal)
            db.insert(TABLE_NAME, null, values)
        }
    }
}

