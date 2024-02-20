package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SquareTests {
    @Test
    void canCalculateArea() {
        assertEquals(9., new Square(3.).area());
    }
    @Test
    void canCalculatePerimeter() {
        assertEquals(20., new Square(5.).perimeter());
    }
    @Test
    void cannotCreateSquareWithNegativeSide() {
        try {
            new Square(-7.);
            Assertions.fail();
        }
        catch (IllegalArgumentException exception) {
            //OK
        }
    }
    @Test
    void testEquality() {
        var square1 = new Square(5.0);
        var square2 = new Square(5.0);
        Assertions.assertEquals(square1, square2);
    }
    @Test
    void testNotEquality() {
        var square1 = new Square(5.0);
        var square2 = new Square(7.0);
        Assertions.assertNotEquals(square1, square2);
    }
    @Test
    void testFail() {
        var square1 = new Square(5.0);
        var square2 = new Square(5.0);
        Assertions.assertTrue(square1.equals(square2));
    }
}
