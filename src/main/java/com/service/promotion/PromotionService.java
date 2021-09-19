package main.java.com.service.promotion;

import main.java.com.constant.PromotionTypeEnum;
import main.java.com.model.promotion.Promotion;

import java.util.HashMap;

public class PromotionService {

    public Promotion getPromotionByProductName(String name) {
        Promotion  promotion = new Promotion();
        switch (name)
        {
            case "A":
                promotion.setProductName("A");
                promotion.setId(1);
                HashMap<String, Integer> map = new HashMap<>();
                map.put("A", 3);
                promotion.setProductQtyMap(map);
                promotion.setAmount(130);
                promotion.setTypeEnum(PromotionTypeEnum.INDIVIDUAL);
                break;
            case "B":
                promotion.setProductName("B");
                promotion.setId(2);
                HashMap<String, Integer> map1 = new HashMap<>();
                map1.put("B", 2);
                promotion.setProductQtyMap(map1);
                promotion.setAmount(45);
                promotion.setTypeEnum(PromotionTypeEnum.INDIVIDUAL);
                break;
            case "C":
                promotion.setProductName("C");
                promotion.setId(3);
                HashMap<String, Integer> map2 = new HashMap<>();
                map2.put("C", 1);
                map2.put("D", 1);
                promotion.setProductQtyMap(map2);
                promotion.setAmount(30);
                promotion.setTypeEnum(PromotionTypeEnum.COMBINATION);
                break;
        }
        return promotion;
    }
}
