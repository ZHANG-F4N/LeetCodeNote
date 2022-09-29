import java.util.Arrays;

public class a1617trimMean {
    public static void main(String[] args) {
        System.out.println(trimMean(new int[]{1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3}));
    }

    public static double trimMean(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length;
        int num = n / 20;
        double ans = 0.0;
        for (int i = num; i < n - num; i++) {
            ans += arr[i];
        }
        return ans / (n - num * 2);
    }
}
