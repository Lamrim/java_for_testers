package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Test;

public class MathTests {

    @Test
    void TestDivideByZero() {
        var x = 1;
        var y = 0;
        var result = x / y;
        System.out.println(result);
    }
}
