public class a1460canBeEqual {
    public static void main(String[] args) {
        System.out.println(canBeEqual(new int[]{1, 2, 3, 4}, new int[]{2, 4, 1, 3}));
    }

    public static boolean canBeEqual(int[] target, int[] arr) {
        int n = arr.length, tot = 0;
        int[] cnt = new int[1010];
        for (int i = 0; i < n; i++) {
            if (++cnt[target[i]] == 1) tot++;
            if (--cnt[arr[i]] == 0) tot--;
        }
        return tot == 0;
    }
}
