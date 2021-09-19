package test.java.com.engine;

import main.java.com.engine.PromotionEngine;
import main.java.com.model.order.Order;
import main.java.com.model.product.Product;
import main.java.com.service.product.ProductService;
import main.java.com.service.promotion.PromotionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
}