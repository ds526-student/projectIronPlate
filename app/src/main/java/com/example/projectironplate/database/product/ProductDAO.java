package com.example.projectironplate.database.product;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projectironplate.database.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private static final String TAG = "ProductDAO";
    private static final String TABLE_NAME = "products";

    // SQL statements
    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
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
                    ");";

    public static final String SQL_DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private final DBHelper dbHelper;

    public ProductDAO(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    /**
     * Insert a new product
     */
    public long insert(Product product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("code", product.getCode());
        values.put("product_name", product.getProductName());
        values.put("serving_size", product.getServingSize());
        values.put("fat_100g", product.getFat());
        values.put("carbohydrates_100g", product.getCarbohydrates());
        values.put("sugars_100g", product.getSugars());
        values.put("fiber_100g", product.getFiber());
        values.put("proteins_100g", product.getProteins());
        values.put("salt_100g", product.getSalt());
        values.put("sodium_100g", product.getSodium());
        values.put("calcium_100g", product.getCalcium());
        values.put("iron_100g", product.getIron());

        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    /**
     * Get product by ID
     */
    public Product getById(int productId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        Product product = null;

        try {
            cursor = db.rawQuery("SELECT rowid, * FROM " + TABLE_NAME + " WHERE rowid = ?",
                    new String[]{String.valueOf(productId)});

            if (cursor.moveToFirst()) {
                product = cursorToProduct(cursor);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error querying product " + productId + ": " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return product;
    }

    /**
     * Search products by name
     */
    public List<Product> searchByName(String searchQuery) {
        List<Product> products = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;

        try {
            cursor = db.rawQuery(
                    "SELECT rowid, * FROM " + TABLE_NAME + " WHERE product_name LIKE ? LIMIT 50",
                    new String[]{"%" + searchQuery + "%"}
            );

            while (cursor.moveToNext()) {
                products.add(cursorToProduct(cursor));
            }
        } catch (Exception e) {
            Log.e(TAG, "Error searching products: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return products;
    }

    /**
     * Get product by barcode
     */
    public Product getByBarcode(String barcode) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        Product product = null;

        try {
            cursor = db.rawQuery("SELECT rowid, * FROM " + TABLE_NAME + " WHERE code = ?",
                    new String[]{barcode});

            if (cursor.moveToFirst()) {
                product = cursorToProduct(cursor);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error querying barcode: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return product;
    }

    /**
     * Get total count of products
     */
    public int getCount() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        int count = 0;

        try {
            cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error counting products: " + e.getMessage());
        } finally {
            if (cursor != null) cursor.close();
            db.close();
        }

        return count;
    }

    /**
     * Update a product
     */
    public int update(Product product) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("code", product.getCode());
        values.put("product_name", product.getProductName());
        values.put("serving_size", product.getServingSize());
        values.put("fat_100g", product.getFat());
        values.put("carbohydrates_100g", product.getCarbohydrates());
        values.put("sugars_100g", product.getSugars());
        values.put("fiber_100g", product.getFiber());
        values.put("proteins_100g", product.getProteins());
        values.put("salt_100g", product.getSalt());
        values.put("sodium_100g", product.getSodium());
        values.put("calcium_100g", product.getCalcium());
        values.put("iron_100g", product.getIron());

        int rows = db.update(TABLE_NAME, values, "ROWID = ?",
                new String[]{String.valueOf(product.getRowId())});
        db.close();
        return rows;
    }

    /**
     * Delete a product
     */
    public int delete(int productId) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows = db.delete(TABLE_NAME, "ROWID = ?",
                new String[]{String.valueOf(productId)});
        db.close();
        return rows;
    }

    /**
     * Convert cursor to Product object
     */
    private Product cursorToProduct(Cursor cursor) {
        return new Product(
                cursor.getInt(cursor.getColumnIndexOrThrow("rowid")),
                cursor.getString(cursor.getColumnIndexOrThrow("code")),
                cursor.getString(cursor.getColumnIndexOrThrow("product_name")),
                cursor.getString(cursor.getColumnIndexOrThrow("serving_size")),
                cursor.getDouble(cursor.getColumnIndexOrThrow("fat_100g")),
                cursor.getDouble(cursor.getColumnIndexOrThrow("carbohydrates_100g")),
                cursor.getDouble(cursor.getColumnIndexOrThrow("sugars_100g")),
                cursor.getDouble(cursor.getColumnIndexOrThrow("fiber_100g")),
                cursor.getDouble(cursor.getColumnIndexOrThrow("proteins_100g")),
                cursor.getDouble(cursor.getColumnIndexOrThrow("salt_100g")),
                cursor.getDouble(cursor.getColumnIndexOrThrow("sodium_100g")),
                cursor.getDouble(cursor.getColumnIndexOrThrow("calcium_100g")),
                cursor.getDouble(cursor.getColumnIndexOrThrow("iron_100g"))
        );
    }
}