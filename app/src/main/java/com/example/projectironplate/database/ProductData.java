package com.example.projectironplate.database;

public class ProductData {
    private int rowId;
    private String code;
    private String productName;
    private String servingSize;
    private double fat100g;
    private double carbohydrates100g;
    private double sugars100g;
    private double fiber100g;
    private double proteins100g;
    private double salt100g;
    private double sodium100g;
    private double calcium100g;
    private double iron100g;

    /**
     * Constructor for ProductData
     * @param rowId The row ID of the product in the database
     * @param code The product code
     * @param productName The product name
     * @param servingSize The serving size of the product
     * @param fat100g The fat content in 100g of the product
     * @param carbohydrates100g The carbohydrates content in 100g of the product
     * @param sugars100g The sugars content in 100g of the product
     * @param fiber100g The fiber content in 100g of the product
     * @param proteins100g The proteins content in 100g of the product
     * @param salt100g The salt content in 100g of the product
     * @param sodium100g The sodium content in 100g of the product
     * @param calcium100g The calcium content in 100g of the product
     * @param iron100g The iron content in 100g of the product
     */
    public ProductData(int rowId, String code, String productName, String servingSize, double fat100g, double carbohydrates100g, double sugars100g, double fiber100g, double proteins100g, double salt100g, double sodium100g, double calcium100g, double iron100g) {
        this.rowId = rowId;
        this.code = code;
        this.productName = productName;
        this.servingSize = servingSize;
        this.fat100g = fat100g;
        this.carbohydrates100g = carbohydrates100g;
        this.sugars100g = sugars100g;
        this.proteins100g = proteins100g;
        this.fiber100g = fiber100g;
        this.salt100g = salt100g;
        this.sodium100g = sodium100g;
        this.calcium100g = calcium100g;
        this.iron100g = iron100g;
    }

    /**
     * collection of getters to access data
     */
    public int getRowId() { return rowId; }
    public String getCode() { return code; }
    public String getProductName() { return productName; }
    public String getServingSize() { return servingSize; }
    public double getFat() { return fat100g; }
    public double getCarbohydrates() { return carbohydrates100g; }
    public double getSugars() { return sugars100g; }
    public double getProteins() { return proteins100g; }
    public double getFiber() { return fiber100g; }
    public double getSalt() { return salt100g; }
    public double getSodium() { return sodium100g; }
    public double getCalcium() { return calcium100g; }
    public double getIron() { return iron100g; }

    /**
     * converts object to a string
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "ProductData{" +
            "rowId=" + rowId +
            ", code='" + code + '\'' +
            ", productName='" + productName + '\'' +
            ", servingSize='" + servingSize + '\'' +
            ", fat100g=" + fat100g + '\'' +
            ", carbohydrates100g=" + carbohydrates100g + '\'' +
            ", sugars100g=" + sugars100g + '\'' +
            ", proteins100g" + proteins100g + '\'' +
            ", fiber100g=" + fiber100g + '\'' +
            ", salt100g=" + salt100g + '\'' +
            ", sodium100g=" + sodium100g + '\'' +
            ", calcium100g=" + calcium100g + '\'' +
            ", iron100g= " + iron100g +
            "}";
    }
}
