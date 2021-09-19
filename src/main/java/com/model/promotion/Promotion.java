package main.java.com.model.promotion;

import main.java.com.constant.PromotionTypeEnum;

import java.util.Map;

public class Promotion {
    int id;
    String productName;
    Map<String, Integer> productQtyMap;
    double amount;
    PromotionTypeEnum typeEnum;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public PromotionTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(PromotionTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, Integer> getProductQtyMap() {
        return productQtyMap;
    }

    public void setProductQtyMap(Map<String, Integer> productQtyMap) {
        this.productQtyMap = productQtyMap;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
