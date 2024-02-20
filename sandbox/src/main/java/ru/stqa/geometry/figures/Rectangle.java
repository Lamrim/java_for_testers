package ru.stqa.geometry.figures;

public class Rectangle {

    private double a;
    private double b;

    public Rectangle(double a, double b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException("Side cannot be negative");
        }
        else {
            this.a = a;
            this.b = b;
        }
    }

    public static void printArea(double a, double b) {
        String text = String.format("Площадь прямоугольника со сторонами %f и %f = %f", a, b, area(a, b));
        System.out.println(text);
    }

    private static double area(double a, double b) {
        return a * b;
    }
}
