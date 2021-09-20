# promotion1
This has 3 model classes, 2 service classes and one Engine class.
A corresponding test class is also added.

Main class to run application is Application.java

Test cases are written using Junit and mockito

# PromotionTypeEnum -
This class is used to define different types of promotions. This can be extended in future to add more promotions.
Corresponding handling needs to be added in PromotionEngine

# Secnarios Covered -
# Scenario A

1 * A 50

1 * B 30

1 * C 20

Total:   100

# Scenario B

5 * A 130 + 2*50

5 * B 45 + 45 + 30

1 * C 20

Total:	370

# Scenario C

5 * A 130 + 2*50

5 * B 45 + 45 + 1 * 30

1 * C -

1 * D 30

Total 380

# Scenario D

5 * A 130 + 2*50

5 * B 45 + 45 + 1 * 30

2 * C -

2 * D 2 * 30

Total 410

# Scenario E

5 * A 130 + 2*50

5 * B 45 + 45 + 1 * 30

2 * C - 1 * 20

2 * D 2 * 30

Total 430

# Scenario F

5 * A 130 + 2*50

5 * B 45 + 45 + 1 * 30

3 * C -

4 * D 3 * 30 + 1 * 15

Total 455



