public class LILISI20220401 {
    public static void main(String[] args) {
        System.out.println(Q1(new int[]{7, 5, 3, 6, 4, 2, 1}));
        System.out.println(Q1(new int[]{1, 2, 3, 4, 5}));
    }

    public static long Q1(int[] arr) {
        int n = arr.length;
        long preSum[] = new long[n + 1];
        long sum = 0L;
        for (int i = 0; i < n; i++) {
            sum += arr[i];
            preSum[i + 1] = preSum[i] + (long) arr[i];
        }
        long res = sum;
        int l = 0, r = n - 1;
        while (l <= r) {
            long midSum = preSum[r + 1] - preSum[l];
            long diff = sum - midSum;
            long val = Math.abs(diff - midSum);
            res = Math.min(val, res);
            if ( arr[l] > arr[r]) {
                l++;
            } else {
                r--;
            }
        }


//        for (int i = 0; i < n; i++) {
//            for (int j = i; j < n; j++) {
//                long midSum = preSum[j + 1] - preSum[i];
//                long diff = sum - midSum;
//                res = Math.min(Math.abs(diff - midSum), res);
//            }
//        }
        return res;
    }
}
