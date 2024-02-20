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
    @Test
    void cannotCreateTriangle() {
        try {
            new Triangle(-5., 7., 3.);
            Assertions.fail();
        }
        catch (IllegalArgumentException exception) {
            //OK
        }
        try {
            new Triangle(5., -7., 3.);
            Assertions.fail();
        }
        catch (IllegalArgumentException exception) {
            //OK
        }
        try {
            new Triangle(5., 7., -3.);
            Assertions.fail();
        }
        catch (IllegalArgumentException exception) {
            //OK
        }
        try {
            new Triangle(10., 50., 30.);
            Assertions.fail();
        }
        catch (IllegalArgumentException exception) {
            //OK
        }
        try {
            new Triangle(0.1, 0.1, 0.3);
            Assertions.fail();
        }
        catch (IllegalArgumentException exception) {
            //OK
        }

    }

    @Test
    void testEquality() {
        var triangle1 = new Triangle(5., 7., 3.);
        var triangle2 = new Triangle(5., 3., 7.);
        Assertions.assertEquals(triangle1, triangle2);

        var triangle3 = new Triangle(5., 7., 3.);
        var triangle4 = new Triangle(7., 5., 3.);
        Assertions.assertEquals(triangle3, triangle4);

        var triangle5 = new Triangle(3., 7., 5.);
        var triangle6 = new Triangle(7., 3., 5.);
        Assertions.assertEquals(triangle5, triangle6);
    }

    @Test
    void testNotEquality() {
        var triangle1 = new Triangle(5.2, 7., 3.);
        var triangle2 = new Triangle(5., 3., 7.);
        Assertions.assertNotEquals(triangle1, triangle2);
    }
}
