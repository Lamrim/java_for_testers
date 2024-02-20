package ru.stqa.geometry.figures;

import java.util.Arrays;
import java.util.Objects;

public class Triangle {
    double a; double b; double c;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;

        double[] t1sides = {this.a, this.b, this.c};
        double[] t2sides = {triangle.a, triangle.b, triangle.c};
        Arrays.sort(t1sides);
        Arrays.sort(t2sides);
        return Arrays.equals(t1sides, t2sides);
    }

    @Override
    public int hashCode() {
        return 1;
    }

    public Triangle(double a, double b, double c) {
        if (a<0. || b<0. || c<0.) {
            throw new IllegalArgumentException("Triangles side cannot be negative");
        }
        if ((a+b)>c & (a+c)>b & (b+c)>a) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        else {
            throw new IllegalArgumentException("Triangle is not exists");
        }
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
