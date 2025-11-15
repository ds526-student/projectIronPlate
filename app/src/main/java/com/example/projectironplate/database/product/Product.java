package com.example.projectironplate.database.product;

/**
 * a table that stores all of the products within the database
 */
public class Product {
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
     * constructor for the Product class
     * @param rowId id of product
     * @param code barcode of product
     * @param productName name of product
     * @param servingSize serving size of product
     * @param fat100g fat content per 100g
     * @param carbohydrates100g carbohydrate content per 100g
     * @param sugars100g sugar content per 100g
     * @param fiber100g fiber content per 100g
     * @param proteins100g protein content per 100g
     * @param salt100g salt content per 100g
     * @param sodium100g sodium content per 100g
     * @param calcium100g calcium content per 100g
     * @param iron100g iron content per 100g
     */
    public Product(int rowId, String code, String productName, String servingSize, 
                   double fat100g, double carbohydrates100g, double sugars100g, 
                   double fiber100g, double proteins100g, double salt100g, 
                   double sodium100g, double calcium100g, double iron100g) {
        this.rowId = rowId;
        this.code = code;
        this.productName = productName;
        this.servingSize = servingSize;
        this.fat100g = fat100g;
        this.carbohydrates100g = carbohydrates100g;
        this.sugars100g = sugars100g;
        this.fiber100g = fiber100g;
        this.proteins100g = proteins100g;
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
     * collection of setters to set data
     */
    public void setRowId(int rowId) { this.rowId = rowId; }
    public void setCode(String code) { this.code = code; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setServingSize(String servingSize) { this.servingSize = servingSize; }
    public void setFat100g(double fat100g) { this.fat100g = fat100g; }
    public void setCarbohydrates100g(double carbohydrates100g) { this.carbohydrates100g = carbohydrates100g; }
    public void setSugars100g(double sugars100g) { this.sugars100g = sugars100g; }
    public void setFiber100g(double fiber100g) { this.fiber100g = fiber100g; }
    public void setProteins100g(double proteins100g) { this.proteins100g = proteins100g; }
    public void setSalt100g(double salt100g) { this.salt100g = salt100g; }
    public void setSodium100g(double sodium100g) { this.sodium100g = sodium100g; }
    public void setCalcium100g(double calcium100g) { this.calcium100g = calcium100g; }
    public void setIron100g(double iron100g) { this.iron100g = iron100g; }

    /**
     * converts object to a string
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        return "Product{" +
                "rowId=" + rowId +
                ", code='" + code + '\'' +
                ", productName='" + productName + '\'' +
                ", servingSize='" + servingSize + '\'' +
                ", fat100g=" + fat100g +
                ", carbohydrates100g=" + carbohydrates100g +
                ", sugars100g=" + sugars100g +
                ", fiber100g=" + fiber100g +
                ", proteins100g=" + proteins100g +
                ", salt100g=" + salt100g +
                ", sodium100g=" + sodium100g +
                ", calcium100g=" + calcium100g +
                ", iron100g=" + iron100g +
                '}';
    }
}
