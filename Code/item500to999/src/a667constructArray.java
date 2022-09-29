import java.util.Arrays;

public class a667constructArray {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(constructArray(5, 3)));
    }

    public static int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        for (int i = 0; i < n - k - 1; i++) ans[i] = i + 1;
        for (int i = n - k - 1, a = n - k, b = n; i < n; ) {
            ans[i++] = a++;
            if (i < n) ans[i++] = b--;
        }


        return ans;
    }
}
