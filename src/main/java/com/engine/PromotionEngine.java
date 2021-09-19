package main.java.com.engine;

import main.java.com.constant.PromotionTypeEnum;
import main.java.com.model.order.Order;
import main.java.com.model.product.Product;
import main.java.com.model.promotion.Promotion;
import main.java.com.service.product.ProductService;
import main.java.com.service.promotion.PromotionService;

import java.util.*;

public class PromotionEngine {

    public double calculateAmount(Order order){
        // get Map of Product and qty

        List<Product> products = order.getProducts();
        Map<String, Integer> product2CountMap = getProduct2CountMap(products);

        // get  list of products from map and iterate and calculate amount for each
        Set<String> productNames = product2CountMap.keySet();
        double totalAmount = 0;
        for (String key : productNames) {
            Promotion promotion = new PromotionService().getPromotionByProductName(key);
            PromotionTypeEnum typeEnum = promotion.getTypeEnum();
            if (typeEnum.equals(PromotionTypeEnum.INDIVIDUAL)) {
                // calculate individual amount
                totalAmount = calculateIndividualAmount();
            } else {
                if (key.equals("C")) {
                    // calculate Combined Amount
                    totalAmount = calculateCombinedAmount();
                    break;
                } else {
                    // do something
                }
            }
        }

        return totalAmount;
    }

    private double calculateCombinedAmount() {
       return 0;
    }

    private double calculateIndividualAmount() {
        return 0;
    }

    public Map<String, Integer> getProduct2CountMap(List<Product> products) {
        HashMap<String, Integer> product2CountMap = new HashMap<>();
        int  countOfA = 0, countOfB = 0, countOfC = 0, countOfD = 0;

        if(products != null && !products.isEmpty()) {
            for (Product product : products) {
                String pname = new ProductService().getName(product);//product.getName();

                switch (pname) {
                    case "A":
                        countOfA++;
                        break;
                    case "B":
                        countOfB++;
                        break;
                    case "C":
                        countOfC++;
                        break;
                    case "D":
                        countOfD++;
                        break;
                }
            }
            product2CountMap.put("A", countOfA);
            product2CountMap.put("B", countOfB);
            product2CountMap.put("C", countOfC);
            product2CountMap.put("D", countOfD);
        }
        return product2CountMap;
    }
}
