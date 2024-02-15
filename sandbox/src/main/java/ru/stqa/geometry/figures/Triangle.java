package ru.stqa.geometry.figures;

public class Triangle {
    double a; double b; double c;
    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public boolean triangleExists() {
        return ((this.a + this.b > this.c)
                & (this.a + this.c > this.b)
                & (this.b + this.c > this.a));
    }

    public double area() {
        double p = perimeter() / 2.;
        return Math.sqrt(p * (p - this.a) * (p - this.b) * (p - this.c));
    }

    public double perimeter() {
        return this.a + this.b + this.c;
    }
}
