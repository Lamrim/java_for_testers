package ru.stqa.geometry.figures;

public class Square {
    double side;
    public Square(double side) {
        if (side < 0) {
            throw new IllegalArgumentException("Side cannot be negative");
        }
        else {this.side = side;}
    }

    public static void printArea(Square s) {
        String text = String.format("Площадь квадрата со стороной %f = %f", s.side, s.area());
        System.out.println(text);
    }

    public double area() {
        return this.side * this.side;
    }

    public double perimeter() {
        return this.side * 4;
    }
}
