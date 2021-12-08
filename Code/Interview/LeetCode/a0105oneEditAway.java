public class a0105oneEditAway {
    public static void main(String[] args) {
        System.out.println(oneEditAway("pale", "ple"));
        System.out.println(oneEditAway("ple", "pale"));
        System.out.println(oneEditAway("pale", "ple"));
    }

    public static boolean oneEditAway(String first, String second) {
        // lStr是长字符串，sStr是短字符串
        String lStr = first.length() >= second.length() ? first : second,
                sStr = first.length() < second.length() ? first : second;
        // diff是字符串中不同的字符数量，sStrIdx是短字符串的index，lStrIdx是长字符串的index
        int diff = 0, sStrIdx = 0, lStrIdx = 0;
        // 如果两个字符串长度差>1，即字符串中不同的字符数量>1，则无法通过一次编辑完成，返回false
        if (lStr.length() - sStr.length() > 1) return false;
        while (sStrIdx < sStr.length()) {
            if (sStr.charAt(sStrIdx++) != lStr.charAt(lStrIdx++)) {
                // 如果字符串长度不同，此时只将长字符串的index向后移动一位
                if (lStr.length() != sStr.length()) sStrIdx--;
                // 字符串不同字符数量+1
                diff++;
            }
            // 字符串中不同的字符数量>1，则无法通过一次编辑完成，返回false
            if (diff > 1) return false;
        }
        return true;
    }
}
