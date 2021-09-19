package main.java.com.model.order;

import main.java.com.model.product.Product;

import java.util.List;

public class Order {
    int id;

    @Override
    public String toString() {
        StringBuilder listOfProducts =  new StringBuilder();
        listOfProducts.append("List of products: ");
        for (Product product : products){
            listOfProducts.append(" ").append(product.getName());
        }
        return  listOfProducts.toString();
    }

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
