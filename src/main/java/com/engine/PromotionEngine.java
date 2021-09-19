package main.java.com.engine;

import main.java.com.constant.PromotionTypeEnum;
import main.java.com.model.order.Order;
import main.java.com.model.product.Product;
import main.java.com.model.promotion.Promotion;
import main.java.com.service.product.ProductService;
import main.java.com.service.promotion.PromotionService;

import java.util.*;

public class PromotionEngine {

    private final PromotionService promotionService;
    private final ProductService productService;

    public PromotionEngine(PromotionService promotionService, ProductService productService){
        this.promotionService = promotionService;
        this.productService = productService;
    }
    
    public double calculateAmount(Order order){
        // get Map of Product and qty

        List<Product> products = order.getProducts();
        Map<String, Integer> product2CountMap = getProduct2CountMap(products);

        // get  list of products from map and iterate and calculate amount for each
        Set<String> productNames = product2CountMap.keySet();
        double totalAmount = 0;
        for (String key : productNames) {
            Promotion promotion = promotionService.getPromotionByProductName(key);
            PromotionTypeEnum typeEnum  =  promotion.getTypeEnum();
            if(typeEnum.equals(PromotionTypeEnum.INDIVIDUAL)){
                // calculate individual amount
                totalAmount = calculateIndividualAmount(product2CountMap, totalAmount, key, promotion);
            }
            else {
                if(key.equals("C")){
                    //calculate combined amount
                    totalAmount = calculateCombinedAmount(product2CountMap, productNames, totalAmount, key, promotion);
                    break;
                }
                else {
                    totalAmount += product2CountMap.get(key) * productService.getProductPriceByProductName(key);
                }
            }
        }

        return totalAmount;
    }

    /**
     *
     * Compare Count of C and D and check 3 different combinations
     * 1. If C == D
     * 2. If C > D
     * 3. If C < D
     *
     */
    private double calculateCombinedAmount(Map<String, Integer> product2CountMap, Set<String> productNames, double totalAmount, String key, Promotion promotion) {
        double productPrice4Key = productService.getProductPriceByProductName(key);
        if(productNames.contains("D")){
            int countOfC = product2CountMap.get(key);
            int countOfD = product2CountMap.get("D");
            double promotionAmount = promotion.getAmount();

            if(countOfC == countOfD){
                totalAmount += countOfC * promotionAmount;
            }
            else {
                if(countOfC > countOfD){
                    totalAmount += countOfD * promotionAmount;
                    totalAmount += (countOfC - countOfD) * productPrice4Key;
                }
                else{
                    totalAmount += countOfC * promotionAmount;
                    totalAmount += (countOfD - countOfC) * productService.getProductPriceByProductName("D");
                }
            }
        }
        else {
            totalAmount += product2CountMap.get(key) * productPrice4Key;
        }
        return totalAmount;
    }

    private double calculateIndividualAmount(Map<String, Integer> product2CountMap, double totalAmount, String key, Promotion promotion) {
        int totalCount = product2CountMap.get(key);
        int promoCount = promotion.getProductQtyMap().get(key);
        double promoAmount =  promotion.getAmount();
        totalAmount += (totalCount / promoCount) * promoAmount;
        totalAmount += (totalCount % promoCount) * productService.getProductPriceByProductName(key);
        return totalAmount;
    }

    public Map<String, Integer> getProduct2CountMap(List<Product> products) {
        HashMap<String, Integer> product2CountMap = new HashMap<>();
        int  countOfA = 0, countOfB = 0, countOfC = 0, countOfD = 0;

        if(products != null && !products.isEmpty()) {
            for (Product product : products) {
                String pname = productService.getName(product);

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
