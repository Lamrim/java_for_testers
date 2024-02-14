package ru.stqa.geometry;

import ru.stqa.geometry.figures.Rectangle;
import ru.stqa.geometry.figures.Square;

public class Geometry {
    public static void main(String[] args) {
        Square.printSquareArea(3.);
        Square.printSquareArea(2.75);

        Rectangle.printRectangleArea(3., 5.7);
    }
}
