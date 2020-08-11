import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        String[] words = scanner.nextLine().split("\\u00A0|\\s");
        scanner.close();

        Map<String, Integer> map = new HashMap<>();
        for (String s : words) {
            String word = s.toLowerCase();
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        map.forEach((k, v) -> System.out.println(String.format("%s %d", k, v)));
    }
}