package test.java.com.engine;

import main.java.com.engine.PromotionEngine;
import main.java.com.service.product.ProductService;
import main.java.com.service.promotion.PromotionService;
import org.junit.jupiter.api.BeforeAll;
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
}
