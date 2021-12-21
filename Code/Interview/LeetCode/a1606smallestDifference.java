import java.util.Arrays;

public class a1606smallestDifference {
    public static void main(String[] args) {
        System.out.println(smallestDifference(new int[]{-2147483648, 1}, new int[]{2147483647, 0}));
        System.out.println(smallestDifference(new int[]{1, 3, 15, 11, 2}, new int[]{23, 127, 235, 19, 8}));
    }

    public static int smallestDifference(int[] a, int[] b) {
        //System.out.println(-Integer.MIN_VALUE);
        Arrays.sort(a);
        Arrays.sort(b);
        int n = a.length;
        int m = b.length;

        long ans = Integer.MAX_VALUE;

        int i = 0, j = 0;
        while (i < a.length && j < b.length) {
            if (a[i] == b[j]) return 0;
            if (a[i] < b[j]) {
                ans = Math.min(ans, (long) b[j] - a[i]);
                i++;
            } else if (a[i] > b[j]) {
                ans = Math.min(ans, (long) a[i] - b[j]);
                j++;
            }
        }


//        for (int i = 0; i < n; i++) {
//            // 二分
//            if (a[i] > b[m - 1]) {
//                ans = Math.min(ans, (long)a[i] - b[m - 1]);
//                continue;
//            }
//            if (a[i] < b[0]) {
//                ans = Math.min(ans, (long)b[0] - a[i]);
//                continue;
//            }
//            int l = 0;
//            int r = m - 1;
//            while (l < r) {
//                int mid = l + ((r - l) >> 1);
//                if (b[mid] == a[i]) return 0;
//                if (b[mid] > a[i]) r = mid;
//                if (b[mid] < a[i]) l = mid + 1;
//            }
//            ans = Math.min(ans, Math.min((long)a[i] - b[r - 1], (long)b[r] - a[i]));
//        }
//        if (a[i] == b[j]) return 0;
//        else if (a[i] > b[j]) {
//            min = Math.min(min, (long) a[i] - (long) b[j]);
//            j++;
//        } else {
//            min = Math.min(min, (long) b[j] - (long) a[i]);
//            i++;
//        }
        return (int) ans;
    }
}
