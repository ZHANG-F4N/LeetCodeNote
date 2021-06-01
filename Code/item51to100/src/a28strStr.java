import java.util.Arrays;

public class a28strStr {
    public static void main(String[] args) {
        System.out.println(strStr("", "a"));
    }

    public static int strStr(String haystack, String needle) {
/*
        A	B	C	A	B	B
next    0	0	0	1	2	0

        a	b	c	a	b	d	d	d	a	b	c	a	b	c
next    0	0	0	1	2	0	0	0	1	2	3	4	5	3
*/

        if (needle.equals("")) {
            return 0;
        }
        int[] next = new int[needle.length()];
        int len = needle.length();
        next[0] = 0;
        int k = 0;
        for (int i = 1; i < len; i++) {
            //Java数组会初始化0,所以不需要对j=0进行处理.
            while (k > 0 && needle.charAt(i) != needle.charAt(k)) {
                k = next[k - 1];
            }
            if (needle.charAt(i) == needle.charAt(k)) {
                next[i] = ++k;
            }
        }

        //System.out.println(Arrays.toString(next));

        int j = 0;
        for (int i = 0; i < haystack.length(); i++) {
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = next[j - 1];
            }
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
                //System.out.println(i + " " + j);
            }
            if (j == needle.length()) {
                return (i - j + 1);
            }
        }
        return -1;
    }

}
