package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {

    @Test
    void CanCalculateArea() {
        Assertions.assertEquals(9., Square.area(3.));
    }

    @Test
    void CanCalculatePerimeter() {
        Assertions.assertEquals(20., Square.perimeter(5.));
    }
}
