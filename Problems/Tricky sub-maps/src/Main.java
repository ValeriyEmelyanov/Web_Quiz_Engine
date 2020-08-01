import java.util.*;

class MapUtils {
    public static Map<Integer, String> getSubMap(TreeMap<Integer, String> map) {
        TreeMap<Integer, String> result = new TreeMap<>(Comparator.reverseOrder());
        if (map.firstKey() % 2 != 0) {
            result.putAll(map.subMap(map.firstKey(), true, map.firstKey() + 4, true));
            int to = map.firstKey() + 4;
            //for (Map.Entry<Integer, String> entry : map.entrySet()) {
            //    if (entry.getKey() > to) {
            //        break;
            //    }
            //    result.put(entry.getKey(), entry.getValue());
            //}
        } else {
            result.putAll(map.subMap(map.lastKey() - 4, true, map.lastKey(), true));
            //int from = map.lastKey() - 4;
            //for (Map.Entry<Integer, String> entry : map.entrySet()) {
            //    if (entry.getKey() < from) {
            //        continue;
            //    }
            //    result.put(entry.getKey(), entry.getValue());
            //}
        }
        return result;
    }
}

/* Do not modify code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TreeMap<Integer, String> map = new TreeMap<>();
        Arrays.stream(scanner.nextLine().split("\\s")).forEach(s -> {
            String[] pair = s.split(":");
            map.put(Integer.parseInt(pair[0]), pair[1]);
        });

        Map<Integer, String> res = MapUtils.getSubMap(map);
        res.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}