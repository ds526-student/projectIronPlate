package com.example.projectironplate.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.projectironplate.database.habitGoals.HabitGoalsDAO;
import com.example.projectironplate.database.product.ProductDAO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper";
    private static final String DATABASE_NAME = "products.db";
    private static final int DATABASE_VERSION = 1;
    private final Context context;
    private final String dbPath;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        this.dbPath = context.getDatabasePath(DATABASE_NAME).getPath();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create products table
        db.execSQL(ProductDAO.SQL_CREATE_TABLE);

        // create habit goals table
        db.execSQL(HabitGoalsDAO.SQL_CREATE_TABLE);
        HabitGoalsDAO.prePopulateTable(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(ProductDAO.SQL_DROP_TABLE);
        onCreate(db);
    }

    /**
     * Initialize database - copy from assets if needed
     */
    public void prepDB() throws IOException {
        ProductDAO productDAO = new ProductDAO(this);

        // Check if database needs to be copied
        if (productDAO.getCount() < 1000) { // Adjust threshold as needed
            copyDatabaseFromAssets();
        }
    }

    private void copyDatabaseFromAssets() throws IOException {
        File dbDir = new File(context.getDatabasePath(DATABASE_NAME).getParent());
        if (!dbDir.exists()) dbDir.mkdirs();

        InputStream input = null;
        try {
            input = context.getAssets().open(DATABASE_NAME);
            OutputStream output = new FileOutputStream(dbPath);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            output.flush();
            output.close();
            input.close();
            Log.d(TAG, "Database copied successfully from assets.");
        } catch (IOException e) {
            Log.w(TAG, "Asset DB not found: " + DATABASE_NAME);
            if (input != null) input.close();
            // onCreate will handle creating empty tables
            SQLiteDatabase db = getWritableDatabase();
            onCreate(db);
            db.close();
        }
    }
}