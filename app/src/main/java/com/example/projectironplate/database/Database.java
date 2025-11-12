package com.example.projectironplate.database;

public class Database {
    private static final String PRODUCT_TABLE_NAME = "products";

    public static final String SQL_DROP_PRODUCT_TABLE =
            "DROP TABLE IF EXISTS " + PRODUCT_TABLE_NAME;
}
