package main.java.com.model.order;

import main.java.com.model.product.Product;

import java.util.List;

public class Order {
    int id;
    List<Product> products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
