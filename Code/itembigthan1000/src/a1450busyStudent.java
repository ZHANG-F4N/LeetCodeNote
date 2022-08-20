public class a1450busyStudent {
    public static void main(String[] args) {
        System.out.println(busyStudent(new int[]{1, 2, 3}, new int[]{3, 2, 7}, 5));
    }

    public static int busyStudent(int[] startTime, int[] endTime, int queryTime) {

        int ans = 0;
        for (int i = 0; i < startTime.length; i++) {
            if (startTime[i] <= queryTime && endTime[i] >= queryTime) ans++;

        }
        return ans;

    }
}
