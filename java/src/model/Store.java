package model;

public class Store {
    private String product;
    private String unit;
    private Integer amount;

    public Store() {
    }

    public Store(String product, String unit, Integer amount) {
        this.product = product;
        this.unit = unit;
        this.amount = amount;
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
}
