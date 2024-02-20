package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RectangleTests {

    @Test
    void cannotCreateRectangleWithNegativeSide() {
        try {
            new Rectangle(-7., 3.);
            Assertions.fail();
        }
        catch (IllegalArgumentException exception) {
            //OK
        }
    }
    @Test
    void testEquality() {
        var rectangle1 = new Rectangle(5.0, 7.0);
        var rectangle2 = new Rectangle(5.0, 7.0);
        Assertions.assertEquals(rectangle1, rectangle2);
    }
    @Test
    void testEquality2() {
        var rectangle1 = new Rectangle(5.0, 7.0);
        var rectangle2 = new Rectangle(7.0, 5.0);
        Assertions.assertEquals(rectangle1, rectangle2);
    }
}
