package model;

public class ShoppingList {
    private String product;
    private String unit;
    private Integer amount;
    private double price;
    private double summary;
    private String mall;

    public ShoppingList() {
    }

    public ShoppingList(String product, String unit, Integer amount, double price, double summary, String mall) {
        this.product = product;
        this.unit = unit;
        this.amount = amount;
        this.price = price;
        this.summary = summary;
        this.mall = mall;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSummary() {
        return summary;
    }

    public void setSummary(double summary) {
        this.summary = summary;
    }

    public String getMall() {
        return mall;
    }

    public void setMall(String mall) {
        this.mall = mall;
    }
}
