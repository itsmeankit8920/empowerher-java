package com.ankit.empowerher;

public class ProductPurchase {
    private String name;
    private String description;
    private int cost; // This should be defined
    private int imageResId;

    public ProductPurchase(String name, String description, int cost, int imageResId) {
        this.name = name;
        this.description = description;
        this.cost = cost;
        this.imageResId = imageResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() { // Ensure this method exists
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
