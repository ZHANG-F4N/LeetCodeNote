import java.util.PriorityQueue;

public class a378kthSmallest {
    public static void main(String[] args) {
        //有序矩阵中第 K 小的元素
        int[][] matrix = {
//                {1, 3, 5, 7, 9, 11},
//                {2, 4, 6, 8, 10, 12},
//                {3, 5, 7, 9, 11, 13},
//                {4, 6, 8, 10, 12, 14},
//                {5, 7, 9, 11, 13, 15},
//                {6, 8, 10, 12, 14, 16},
//                {1, 5, 9},
//                {10, 11, 13},
//                {12, 13, 15}
                {1, 2},
                {1, 3},
        };
        int k = 2;
        System.out.println(kthSmallest(matrix, k));
    }

    public static int kthSmallest(int[][] matrix, int k) {

        int m = matrix.length;
        int n = matrix[0].length;

        int max = matrix[m - 1][n - 1];
        int min = matrix[0][0];

        while (max > min) {
            // max+min 会导致 溢出
            // int mid = (max + min) / 2;
            int mid = min + ((max - min) >> 1);
            System.out.println(mid + "-" + search(matrix, mid));
            if (search(matrix, mid) >= k) {
                max = mid;
                continue;
            }
            if (search(matrix, mid) < k) {
                min = mid + 1;
                continue;

            }
        }
        return min;

    }

    public static int search(int[][] matrix, int tar) {
        int num = 0;

        int row = matrix.length - 1;
        int col = matrix[0].length - 1;
        int i = 0;
        int j = col;

        while (i <= row && j >= 0) {
            if (matrix[i][j] == tar) {
                num += j + 1;
                i++;
                //j--;
                continue;
            }
            if (matrix[i][j] < tar) {
                i++;
                num += j + 1;
                continue;
            }
            if (matrix[i][j] > tar) {
                j--;
                continue;
            }
        }
        return num;
    }


    //归并排序解决
    public static int kthSmallestMerge(int[][] matrix, int k) {

        //PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        int m = matrix.length;
        int n = matrix[0].length;

        int[] arr = new int[m + n + 1];
        int index = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[index] = matrix[i][j];
            }
        }

        return 0;

    }
}
