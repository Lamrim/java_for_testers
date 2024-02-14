package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;

public class Geometry {
    public static void main(String[] args) {
        Square.printArea(3.);
        Square.printArea(2.75);

        Rectangle.printArea(3., 5.7);
    }
}
