package com.example.projectironplate.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.projectironplate.database.habitGoals.HabitGoalsDAO
import com.example.projectironplate.database.product.ProductDAO
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

class DBHelper(private val context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    private val dbPath: String

    init {
        this.dbPath = context.getDatabasePath(DATABASE_NAME).getPath()
    }

    override fun onCreate(db: SQLiteDatabase) {
        // create products table
        db.execSQL(ProductDAO.SQL_CREATE_TABLE)

        // create habit goals table
        db.execSQL(HabitGoalsDAO.SQL_CREATE_TABLE)
        HabitGoalsDAO.prePopulateTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(ProductDAO.SQL_DROP_TABLE)
        onCreate(db)
    }

    /**
     * Initialize database - copy from assets if needed
     */
    @Throws(IOException::class)
    fun prepDB() {
        val productDAO = ProductDAO(this)

        // Check if database needs to be copied
        if (productDAO.getCount() < 1000) { // Adjust threshold as needed
            copyDatabaseFromAssets()
        }
    }

    @Throws(IOException::class)
    private fun copyDatabaseFromAssets() {
        val dbDir = File(context.getDatabasePath(DATABASE_NAME).getParent())
        if (!dbDir.exists()) dbDir.mkdirs()

        var input: InputStream? = null
        try {
            input = context.getAssets().open(DATABASE_NAME)
            val output: OutputStream = FileOutputStream(dbPath)
            val buffer = ByteArray(1024)
            var length: Int
            while ((input.read(buffer).also { length = it }) > 0) {
                output.write(buffer, 0, length)
            }
            output.flush()
            output.close()
            input.close()
            Log.d(TAG, "Database copied successfully from assets.")
        } catch (e: IOException) {
            Log.w(TAG, "Asset DB not found: " + DATABASE_NAME)
            if (input != null) input.close()
            // onCreate will handle creating empty tables
            val db = getWritableDatabase()
            onCreate(db)
            db.close()
        }
    }

    companion object {
        private const val TAG = "DBHelper"
        private const val DATABASE_NAME = "products.db"
        private const val DATABASE_VERSION = 1
    }
}