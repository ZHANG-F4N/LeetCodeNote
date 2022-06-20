import java.util.ArrayList;

public class a1717multiSearch {
    public static void main(String[] args) {
        multiSearch("mississippi", new String[]{"is", "ppi", "hi", "sis", "i", "ssippi"});
    }

    public static int[][] multiSearch(String big, String[] smalls) {
        int n = smalls.length;
        int[][] ans = new int[n][];
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> list = new ArrayList<>();
            String str = smalls[i];
            if ("".equals(str)) {
                ans[i] = new int[]{};
                continue;
            }
            int j = 0;
            while (j < big.length()) {
                int val = big.indexOf(str, j);
                if (val != -1) {
                    list.add(val);
                    j = val + 1;
                } else break;
            }
            int[] ints = new int[list.size()];
            for (int k = 0; k < list.size(); k++) {
                ints[k] = list.get(k);
            }
            ans[i] = ints;
        }
        return ans;
    }
}
