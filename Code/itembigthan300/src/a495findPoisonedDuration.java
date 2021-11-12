public class a495findPoisonedDuration {
    public static void main(String[] args) {
        System.out.println(findPoisonedDuration(new int[]{1, 4}, 2));
        System.out.println(findPoisonedDuration(new int[]{1, 2}, 2));
    }

    public static int findPoisonedDuration(int[] timeSeries, int duration) {
        int ans = 0;

        if (timeSeries.length == 0 || duration == 0) {
            return 0;
        }
        int endtime = timeSeries[0] + duration;
        for (int i = 1; i < timeSeries.length; i++) {
            if (endtime > timeSeries[i]) {
                ans += timeSeries[i] - timeSeries[i - 1];
            } else {
                ans += duration;
            }
            endtime = timeSeries[i] + duration;
        }
        ans += duration;

        return ans;
    }
}
