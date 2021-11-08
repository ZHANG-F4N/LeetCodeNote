public class a299getHint {
    public static void main(String[] args) {
        System.out.println(getHint("1123", "0111"));
        System.out.println(getHint("1807", "7810"));
        System.out.println(getHint("1", "0"));
    }

    public static String getHint(String secret, String guess) {
        int N = secret.length();
        // compute Bulls
        char[] right = secret.toCharArray();
        char[] gue = guess.toCharArray();
        int[] num1 = new int[10];
        int[] num2 = new int[10];
        int count1 = 0;
        for (int i = 0; i < N; i++) {
            if (right[i] == gue[i]) {
                count1++;
                gue[i] = '*';
                right[i] = '*';
            }
            if (right[i] != '*')
                num1[right[i] - '0']++;
            if (gue[i] != '*')
                num2[gue[i] - '0']++;
        }
        int count2 = 0;
        for (int i = 0; i < 10; i++) {
            count2 += Math.min(num1[i] , num2[i]);
        }
        return count1 + "A" + count2 + "B";
    }
}
