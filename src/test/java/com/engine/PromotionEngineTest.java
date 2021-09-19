package test.java.com.engine;

import main.java.com.constant.PromotionTypeEnum;
import main.java.com.engine.PromotionEngine;
import main.java.com.model.order.Order;
import main.java.com.model.product.Product;
import main.java.com.model.promotion.Promotion;
import main.java.com.service.product.ProductService;
import main.java.com.service.promotion.PromotionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.mockito.Mockito.*;

public class PromotionEngineTest {

    public static PromotionService promotionService;
    public static ProductService productService;
    public static PromotionEngine instance;

    @BeforeAll
    public static void setup() throws Exception {
        promotionService = mock(PromotionService.class);
        productService = mock(ProductService.class);
        instance  = spy(new PromotionEngine(promotionService, productService));
    }

    @Test
    public void calculateAmount_whenpromotionService_GetPromotionByProductName_returnsNull_throwsNPE() {
        Order order = mock(Order.class);
        List<Product> products = new ArrayList<>();
        Product product = mock(Product.class);
        product.setName("test");
        products.add(product);

        Map<String, Integer> map = new HashMap<>();
        map.put("test", 0);
        doReturn(map).when(instance).getProduct2CountMap(anyList());

        when(this.promotionService.getPromotionByProductName(anyString())).thenReturn(null);
        Assertions.assertThrows(NullPointerException.class, () -> {
            instance.calculateAmount(order);
        });

    }

    @Test
    public void calculateAmount_whengetProduct2CountMap_returnsEmptyMap_thenReturn0() {
        Order order = mock(Order.class);
        List<Product> products = new ArrayList<>();
        Product product = mock(Product.class);
        product.setName("test");
        products.add(product);
        Promotion promotion = mock(Promotion.class);

        Map<String, Integer> map = new HashMap<>();
        map.put("test", 0);
        doReturn(map).when(instance).getProduct2CountMap(anyList());

        when(promotionService.getPromotionByProductName("test")).thenReturn(promotion);
        doReturn(Collections.emptyMap()).when(instance).getProduct2CountMap(anyList());

        double amount = instance.calculateAmount(order);
        Assertions.assertEquals(0, amount);
    }

    @Test
    public void calculateAmount_whenPromotion_getTypeEnum_returnsNull_throwsNPE() {
        Order order = mock(Order.class);
        List<Product> products = new ArrayList<>();
        Product product = mock(Product.class);
        product.setName("test");
        products.add(product);

        Map<String, Integer> map = new HashMap<>();
        map.put("test", 0);
        doReturn(map).when(instance).getProduct2CountMap(anyList());

        Promotion promotion = mock(Promotion.class);

        when(promotionService.getPromotionByProductName("test")).thenReturn(promotion);

        when(productService.getProductPriceByProductName("test")).thenReturn(Double.valueOf(0));
        Assertions.assertThrows(NullPointerException.class, () -> {
            instance.calculateAmount(order);
        });
    }

    @Test
    public void calculateAmountIndividual_when_getProductQtyMap_returnsNull_throwsNPE() {
        Order order = mock(Order.class);
        List<Product> products = new ArrayList<>();
        Product product = mock(Product.class);
        product.setName("test");
        products.add(product);

        Map<String, Integer> map = new HashMap<>();
        map.put("test", 1);
        doReturn(map).when(instance).getProduct2CountMap(anyList());

        Promotion promotion = mock(Promotion.class);
        when(promotion.getTypeEnum()).thenReturn(PromotionTypeEnum.INDIVIDUAL);

        when(promotionService.getPromotionByProductName("test")).thenReturn(promotion);

        Assertions.assertThrows(NullPointerException.class, () -> {
            instance.calculateAmount(order);
        });
    }

    @Test
    public void calculateAmountIndividual_when_getAmount_returnsNull_throwsNPE() {
        Order order = mock(Order.class);
        List<Product> products = new ArrayList<>();
        Product product = mock(Product.class);
        product.setName("test");
        products.add(product);

        Map<String, Integer> map = new HashMap<>();
        map.put("test", 3);
        doReturn(map).when(instance).getProduct2CountMap(anyList());

        Promotion promotion = mock(Promotion.class);
        when(promotion.getTypeEnum()).thenReturn(PromotionTypeEnum.INDIVIDUAL);
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("test", 2);
        when(promotion.getProductQtyMap()).thenReturn(map1);
        when(promotion.getAmount()).thenReturn(Double.valueOf(20));
        when(productService.getProductPriceByProductName("test")).thenReturn(Double.valueOf(15));

        when(promotionService.getPromotionByProductName("test")).thenReturn(promotion);

        double amount = instance.calculateAmount(order);
        Assertions.assertEquals(35, amount);

    }

    @Test
    public void calculateAmountCombination_when_ProductName_test_returnsuccess() {
        Order order = mock(Order.class);
        List<Product> products = new ArrayList<>();
        Product product = mock(Product.class);
        product.setName("test");
        products.add(product);

        Map<String, Integer> map = new HashMap<>();
        map.put("test", 1);
        doReturn(map).when(instance).getProduct2CountMap(anyList());

        Promotion promotion = mock(Promotion.class);
        when(promotion.getTypeEnum()).thenReturn(PromotionTypeEnum.COMBINATION);
        when(productService.getProductPriceByProductName("test")).thenReturn(Double.valueOf(15));


        when(promotionService.getPromotionByProductName("test")).thenReturn(promotion);


        double amount = instance.calculateAmount(order);
        Assertions.assertEquals(15, amount);

    }

    @Test
    public void calculateAmountCombination_when_ProductName_valid_returnsuccess() {
        Order order = mock(Order.class);
        List<Product> products = new ArrayList<>();
        Product product = mock(Product.class);
        product.setName("C");
        products.add(product);

        Map<String, Integer> map = new HashMap<>();
        map.put("C", 2);
        doReturn(map).when(instance).getProduct2CountMap(anyList());

        Promotion promotion = mock(Promotion.class);
        when(promotion.getTypeEnum()).thenReturn(PromotionTypeEnum.COMBINATION);
        when(productService.getProductPriceByProductName("C")).thenReturn(Double.valueOf(15));


        when(promotionService.getPromotionByProductName("C")).thenReturn(promotion);


        double amount = instance.calculateAmount(order);
        Assertions.assertEquals(30, amount);

    }

    @Test
    public void calculateAmountCombination_when_CountOfCEqualsCountOfD_returnCombinationAmount() {
        Order order = mock(Order.class);
        List<Product> products = new ArrayList<>();
        Product product = mock(Product.class);
        product.setName("C");
        products.add(product);

        Map<String, Integer> map = new HashMap<>();
        map.put("C", 1);
        map.put("D", 1);
        doReturn(map).when(instance).getProduct2CountMap(anyList());

        Promotion promotion = mock(Promotion.class);
        when(promotion.getTypeEnum()).thenReturn(PromotionTypeEnum.COMBINATION);
        when(promotion.getAmount()).thenReturn(Double.valueOf(20));
        when(productService.getProductPriceByProductName("C")).thenReturn(Double.valueOf(15));


        when(promotionService.getPromotionByProductName("C")).thenReturn(promotion);


        double amount = instance.calculateAmount(order);
        Assertions.assertEquals(20, amount);

    }

    @Test
    public void calculateAmountCombination_when_CountOfCGreaterThanCountOfD_returnCombinationAmount() {
        Order order = mock(Order.class);
        List<Product> products = new ArrayList<>();
        Product product = mock(Product.class);
        product.setName("C");
        products.add(product);

        Map<String, Integer> map = new HashMap<>();
        map.put("C", 2);
        map.put("D", 1);
        doReturn(map).when(instance).getProduct2CountMap(anyList());

        Promotion promotion = mock(Promotion.class);
        when(promotion.getTypeEnum()).thenReturn(PromotionTypeEnum.COMBINATION);
        when(promotion.getAmount()).thenReturn(Double.valueOf(20));
        when(productService.getProductPriceByProductName("C")).thenReturn(Double.valueOf(15));


        when(promotionService.getPromotionByProductName("C")).thenReturn(promotion);


        double amount = instance.calculateAmount(order);
        Assertions.assertEquals(35, amount);

    }

    @Test
    public void calculateAmountCombination_when_CountOfCLessThanCountOfD_returnCombinationAmount() {
        Order order = mock(Order.class);
        List<Product> products = new ArrayList<>();
        Product product = mock(Product.class);
        product.setName("C");
        products.add(product);

        Map<String, Integer> map = new HashMap<>();
        map.put("C", 2);
        map.put("D", 1);
        doReturn(map).when(instance).getProduct2CountMap(anyList());

        Promotion promotion = mock(Promotion.class);
        when(promotion.getTypeEnum()).thenReturn(PromotionTypeEnum.COMBINATION);
        when(promotion.getAmount()).thenReturn(Double.valueOf(20));
        when(productService.getProductPriceByProductName("C")).thenReturn(Double.valueOf(30));


        when(promotionService.getPromotionByProductName("C")).thenReturn(promotion);


        double amount = instance.calculateAmount(order);
        Assertions.assertEquals(50, amount);

    }
}
