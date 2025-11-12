package com.example.projectironplate.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DBHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBHelper"; // tag for logs
    private static final String DATABASE_NAME = "products.db"; // db file name
    private static final int DATABASE_VERSION = 1; // current db version
    private final Context context; // application context
    private final String dbPath; // path to db

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        this.dbPath = context.getDatabasePath(DATABASE_NAME).getPath();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    /**
     * checks for the existance of the db
     * @throws IOException if the db is not found
     */
    public void prepDB() throws IOException {
        SQLiteDatabase db = getWritableDatabase();

        String product = logProductDetails(20000);

        // recreate db if there are less than MIN_DB_ITEMS
        if (product == null) {
            db.execSQL(Database.SQL_DROP_PRODUCT_TABLE);
            copyDB();
        }
    }

    /**
     * copies the database from assets to the app's internal storage
     * @throws IOException if the database cannot be copied
     */
    private void copyDB() throws IOException {
        File dbDir = new File(context.getDatabasePath(DATABASE_NAME).getParent());
        if (!dbDir.exists()) dbDir.mkdirs();

        InputStream input = null;
        try {
            // copies db from assets
            input = context.getAssets().open(DATABASE_NAME);

            // constructs the output stream
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
            // if db not found
            Log.w(TAG, "Asset DB not found: " + DATABASE_NAME + "; creating empty database instead.");
            if (input != null) input.close();
            createEmptyDB();
        }
    }

    /**
     * creates an empty db with a products table
     */
    private void createEmptyDB() {
        SQLiteDatabase db = null;
        try {
            db = getWritableDatabase();
            db.execSQL("CREATE TABLE IF NOT EXISTS products (" +
                    "ROWID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "code TEXT, " +
                    "product_name TEXT, " +
                    "serving_size TEXT, " +
                    "fat_100g REAL, " +
                    "carbohydrates_100g REAL, " +
                    "sugars_100g REAL, " +
                    "fiber_100g REAL, " +
                    "proteins_100g REAL, " +
                    "salt_100g REAL, " +
                    "sodium_100g REAL, " +
                    "calcium_100g REAL, " +
                    "iron_100g REAL" +
                    ");");
            Log.d(TAG, "empty db created");
        } catch (Exception ex) {
            Log.e(TAG, "error creating empty DB: " + ex.getMessage());
        } finally {
            if (db != null) db.close();
        }
    }

    /**
     * checks the record count
     * @return the record count
     */
    public int getRecordCount() {
        int count = 0;
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
            cursor = db.rawQuery("SELECT COUNT(*) FROM products", null);
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error reading database: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            if (db != null) db.close();
        }
        return count;
    }

    /**
     * Query and log details for a specific product by ROWID
     * @param productId The ROWID of the product to query
     */
    public String logProductDetails(int productId) {
        SQLiteDatabase db = null;
        Cursor cursor = null;
        try {
            db = getReadableDatabase();
            cursor = db.rawQuery("SELECT rowid, * FROM products WHERE rowid = ?", new String[]{String.valueOf(productId)});

            if (cursor.moveToFirst()) {
                return cursor.getString(cursor.getColumnIndexOrThrow("product_name"));
            } else {
                Log.d(TAG, "No product found with ID: " + productId);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error querying product " + productId + ": " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            if (db != null) db.close();
        }

        return null;
    }
}