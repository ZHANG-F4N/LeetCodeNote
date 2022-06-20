import java.util.Arrays;

public class a1089duplicateZeros {
    public static void main(String[] args) {
        duplicateZeros(new int[]{0, 0, 0, 0, 0, 0, 0});
        duplicateZeros(new int[]{1, 0, 2, 3, 0, 4, 5, 0});
    }

    public static void duplicateZeros(int[] arr) {
        int n = arr.length;

        int[] num = new int[n];
        for (int i = 1; i < n; i++) {
            if (arr[i - 1] == 0) num[i] = num[i - 1] + 1;
            else num[i] = num[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
//            if (num[i] == 0) return;
            if (num[i] + i >= n) continue;
            if (arr[i] == 0) {
                arr[i + num[i]] = 0;
                if (num[i] + i + 1 < n) arr[i + num[i] + 1] = 0;
            } else {
                arr[i + num[i]] = arr[i];
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
