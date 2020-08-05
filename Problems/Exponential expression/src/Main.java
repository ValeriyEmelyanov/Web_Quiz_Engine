import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final double x = scanner.nextDouble();
        scanner.close();

        Double result = Math.pow(x, 3.0) + Math.pow(x, 2.0) + x + 1.0;

        System.out.println(result);
    }
}