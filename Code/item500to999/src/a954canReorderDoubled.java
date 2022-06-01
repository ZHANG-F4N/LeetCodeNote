import java.util.*;

public class a954canReorderDoubled {
    public static void main(String[] args) {
        System.out.println(canReorderDoubled(new int[]{-33, 0}));
        System.out.println(canReorderDoubled(new int[]{0, 0, 0, 0, 0, 0}));
        System.out.println(canReorderDoubled(new int[]{2, 4, 0, 0, 8, 1}));


        System.out.println(canReorderDoubled(new int[]{4, -2, 2, -4}));
        System.out.println(canReorderDoubled(new int[]{2, 1, 2, 6}));
        System.out.println(canReorderDoubled(new int[]{3, 1, 3, 6}));
    }

    public static boolean canReorderDoubled(int[] arr) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i : arr) {
            hashMap.put(i, hashMap.getOrDefault(i, 0) + 1);
        }
        int num = arr.length;
        if (hashMap.getOrDefault(0, 0) % 2 == 1) return false;
        List<Integer> list = new ArrayList<Integer>();
        for (Map.Entry<Integer, Integer> en : hashMap.entrySet()) {
            list.add(en.getKey());
        }
        Collections.sort(list, (a, b) -> Math.abs(a) - Math.abs(b));
        for (Integer val : list) {
            if (hashMap.getOrDefault(val*2,0) < hashMap.get(val)) return false;
            hashMap.put(val*2,hashMap.getOrDefault(val*2,0) - hashMap.get(val));
        }
        return true;

    }
}
