import java.util.Arrays;

public class a10_01merge {
    public static void main(String[] args) {
        int[] A = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] B = {2, 5, 6};
        int n = 3;
        merge(A, m, B, n);
        System.out.println(Arrays.toString(A));
    }

    public static void merge(int[] A, int m, int[] B, int n) {
        int i = m - 1;
        int j = n - 1;
        int index = n + m - 1;
        while (i >= 0 && j >= 0) {
            if (A[i] > B[j]) {
                A[index--] = A[i--];
            } else {
                A[index--] = B[j--];
            }
        }
        while (i >= 0) {
            A[index--] = A[i--];
        }
        while (j >= 0) {
            A[index--] = B[j--];
        }
        return;
    }
}
