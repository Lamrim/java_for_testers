package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SquareTests {

    @Test
    void canCalculateArea() {
        Assertions.assertEquals(9., new Square(3.).area());
    }

    @Test
    void canCalculatePerimeter() {
        Assertions.assertEquals(20., new Square(5.).perimeter());
    }
}
