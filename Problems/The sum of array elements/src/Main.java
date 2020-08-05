import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final int len = scanner.nextInt();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = scanner.nextInt();
        }
        scanner.close();

        int result = Arrays.stream(arr).sum();

        System.out.println(result);
    }
}