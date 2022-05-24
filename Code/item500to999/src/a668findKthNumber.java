public class a668findKthNumber {
    public static void main(String[] args) {
        System.out.println(findKthNumber(3, 3, 5));
    }

    public static int findKthNumber(int m, int n, int k) {
        int l = 1, r = m * n;
        while (l < r) {
            int mid = l + r >> 1;
            if (check(mid, m, n, k)) r = mid;
            else l = mid + 1;
        }
        return r;
    }


    public static boolean check(int mid, int m, int n, int k) {
        int count = 0;
        for (int i = 1; i <= m; i++) {
            count += Math.min(n, mid / i);
        }
        //  System.out.println("count ==> " + count);
        return count >= k;
    }
}
