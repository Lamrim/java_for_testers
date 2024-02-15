package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TriangleTests {

    @Test
    void canCalculateArea() {
        Assertions.assertEquals(18.3267, new Triangle(5.3, 7., 8.1 ).area(), 0.0001);
        Assertions.assertEquals(0.0175, new Triangle(0.18, 0.2, 0.3 ).area(), 0.0001);
    }

    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(24.3, new Triangle(5., 7.3, 12.).perimeter());
        Assertions.assertEquals(2.55, new Triangle(0.98, 1.2, 0.37).perimeter());
    }
}
