import java.util.Arrays;

public class a1109corpFlightBookings {
    public static void main(String[] args) {
        int[][] bookings = {{1, 2, 10}, {2, 3, 20}, {2, 5, 25}};

        int n = 5;
        int[] ans = corpFlightBookings(bookings, n);

        System.out.println(Arrays.toString(ans));


    }

    // 暴力
//    public static int[] corpFlightBookings(int[][] bookings, int n) {
//        if (n == 0){
//            return new int[0];
//        }
//        int[] ans = new int[n + 1];
//        for (int i = 0; i < bookings.length; i++) {
//            for (int j = bookings[i][0]; j <= bookings[i][1]; j++) {
//                ans[j] += bookings[i][2];
//            }
//        }
//        return Arrays.copyOfRange(ans, 1, ans.length);
//    }
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ans = new int[n + 1];


        for (int i = 0; i < bookings.length; i++) {
            ans[bookings[i][0]] += bookings[i][2];
            if (bookings[i][1] + 1 <= n) {
                ans[bookings[i][1] + 1] -= bookings[i][2];
            }
        }
        for (int i = 1; i < n + 1; i++) {
            ans[i] += ans[i - 1];
        }
        return Arrays.copyOfRange(ans, 1, ans.length);
    }
}
