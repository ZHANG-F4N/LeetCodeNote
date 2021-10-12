import java.util.Arrays;

public class a66constructArr {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(constructArr(arr)));
    }

    public static int[] constructArr(int[] a) {
        if (a.length == 0) {
            return a;
        }
        int N = a.length;
        int[] left = new int[N];
        int[] right = new int[N];
        left[0] = 1;
        right[N - 1] = 1;
        int[] ans = new int[N];

        for (int i = 1; i < N; i++) {
            left[i] = left[i - 1] * a[i - 1];
            right[N - i - 1] = right[N - i] * a[N - i];
        }
        for (int i = 0; i < N; i++) {
            ans[i] = left[i] * right[i];
        }
        return ans;
    }
}
