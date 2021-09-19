package main.java.com.service.product;

import main.java.com.model.product.Product;

public class ProductService {

    public double getProductPriceByProductName(String name) {
        double price = 0;
        switch (name)
        {
            case "A":
                price = 50;
                break;
            case "B":
                price = 30;
                break;
            case "C":
                price = 20;
                break;
            case "D":
                price = 15;
                break;
        }
        return price;
    }

    public String getName(Product product) {
        return product.getName();
    }
}
