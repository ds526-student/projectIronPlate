package com.example.projectironplate.database;

public class Database {
    private static final String PRODUCT_TABLE_NAME = "products";
    private static final String HABITS_TABLE_NAME = "habits";
    private static final String HABITS_GOALS_TABLE_NAME = "habitGoals";

    public static final String SQL_CREATE_PRODUCT_TABLE =
            "CREATE TABLE IF NOT EXISTS products (" +
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

    public static final String SQL_DROP_PRODUCT_TABLE =
            "DROP TABLE IF EXISTS " + PRODUCT_TABLE_NAME;



    public static final String SQL_CREATE_HABITS_TABLE =
            "CREATE TABLE IF NOT EXISTS habits (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "habit TEXT, " +
            "value REAL, " +
            "date DATETIME" +
            ");";

    public static final String SQL_DROP_HABITS_TABLE =
            "DROP TABLE IF EXISTS " + HABITS_TABLE_NAME;



    public static final String SQL_CREATE_HABIT_GOALS_TABLE =
            "CREATE TABLE IF NOT EXISTS habit_goals (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "habit TEXT, " +
            "goal REAL" +
            ");";

    public static final String SQL_DROP_HABIT_GOALS_TABLE =
            "DROP TABLE IF EXISTS " + HABITS_GOALS_TABLE_NAME;
}
