public class Hello {
    public static void main(String[] args) {

        int x = 1;
        int y = 0;

        if (y == 0) {
            System.out.println("Division by zero is not allowed");
        }
        else {
            int result = divide(x, y);
            System.out.println("Hello, world!");}
    }

    private static int divide(int x, int y) {
        return x / y;
    }
}
