public class a6convert {
    public static void main(String[] args) {
        System.out.println(convert("A", 1));
        System.out.println(convert("PAYPALISHIRING", 4));
        System.out.println(convert("PAYPALISHIRING", 5));
    }

    public static String convert(String s, int numRows) {
        if (numRows == 1) return s;
        char[] arr = s.toCharArray();
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            int idx = i;
            while (idx < s.length()) {
                ans.append(arr[idx]);
                int delta = 2 * (numRows - i - 1);
                if (delta != 0 && delta < 2 * (numRows - 1) && idx + delta < s.length()) {
                    ans.append(arr[idx + 2 * (numRows - i - 1)]);
                }
                idx += 2 * (numRows - 1);
            }
        }
        return ans.toString();
    }
}
