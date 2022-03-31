public class a1629slowestKey {
    public static void main(String[] args) {
        System.out.println(slowestKey(new int[]{28,65,97}, "gaf"));
        System.out.println(slowestKey(new int[]{9, 29, 49, 50}, "cbcd"));

    }

    public static char slowestKey(int[] releaseTimes, String keysPressed) {
        int pre = releaseTimes[0];
        for (int i = 1; i < releaseTimes.length; i++) {
            int temp = releaseTimes[i];
            releaseTimes[i] = releaseTimes[i] - pre;
            pre = temp;
        }
        int max = releaseTimes[0];
        char ans = keysPressed.charAt(0);
        for (int i = 1; i < releaseTimes.length; i++) {
            if (releaseTimes[i] > max){
                max = releaseTimes[i];
                ans = keysPressed.charAt(i);
            }
            else if (releaseTimes[i] == max) ans = (char) Math.max(ans, keysPressed.charAt(i));
        }
        return ans;
    }
}
