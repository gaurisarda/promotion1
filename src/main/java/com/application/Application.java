package main.java.com.application;

import main.java.com.engine.PromotionEngine;
import main.java.com.model.order.Order;
import main.java.com.model.product.Product;
import main.java.com.service.product.ProductService;
import main.java.com.service.promotion.PromotionService;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static PromotionService promotionService = new PromotionService();
    public static ProductService productService = new ProductService();

    public static void main(String[] args){
        Order order1 = new Order();
        List<Product> products = new ArrayList<>();

        products.add(new Product("A"));
        products.add(new Product("B"));
        products.add(new Product("C"));
        order1.setProducts(products);


        PromotionEngine eng = new PromotionEngine(promotionService, productService);
        double amount = eng.calculateAmount(order1);
        System.out.println(" Order1 : " + order1 +  " Amount : " + amount);
        Order order2 = new Order();

        products.add(new Product("A"));
        products.add(new Product("A"));
        products.add(new Product("A"));
        products.add(new Product("A"));
        products.add(new Product("B"));
        products.add(new Product("B"));
        order1.setProducts(products);
        amount = eng.calculateAmount(order1);
        System.out.println(" Order2 : " + order1 +  " Amount : " + amount);

        products.add(new Product("D"));
        order1.setProducts(products);
        amount = eng.calculateAmount(order1);
        System.out.println(" Order3 : " + order1 +  " Amount : " + amount);

        products.add(new Product("C"));
        products.add(new Product("D"));
        order1.setProducts(products);
        amount = eng.calculateAmount(order1);
        System.out.println(" Order4 : " + order1 +  " Amount : " + amount);

        products.add(new Product("C"));
        order1.setProducts(products);
        amount = eng.calculateAmount(order1);
        System.out.println(" Order5 : " + order1 +  " Amount : " + amount);

        products.add(new Product("D"));
        products.add(new Product("D"));
        order1.setProducts(products);
        amount = eng.calculateAmount(order1);
        System.out.println(" Order6 : " + order1 +  " Amount : " + amount);
    }
}
