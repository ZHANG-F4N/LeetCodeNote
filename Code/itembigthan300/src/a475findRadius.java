import java.util.ArrayList;
import java.util.Arrays;

public class a475findRadius {
    public static void main(String[] args) {
        System.out.println(findRadius(new int[]{1}, new int[]{1, 2, 3, 4}));
        System.out.println(findRadius(new int[]{1, 2, 3, 4}, new int[]{1, 4}));
    }

    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int radius = 0;

        for (int i = 0, j = 0; i < houses.length; i++) {
            int curDistance = Math.abs(houses[i] - heaters[j]);
            while (j < heaters.length - 1 && Math.abs(houses[i] - heaters[j]) >= Math.abs(houses[i] - heaters[j + 1])) {
                j++;
                curDistance = Math.min(curDistance, Math.abs(houses[i] - heaters[j]));
            }
            radius = Math.max(radius, curDistance);
        }
        return radius;
    }
}
