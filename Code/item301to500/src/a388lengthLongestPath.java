import java.util.HashMap;

public class a388lengthLongestPath {
    public static void main(String[] args) {
        System.out.println(lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
    }

    public static int lengthLongestPath(String input) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        char[] chars = input.toCharArray();
        String ans = null;
        int n = chars.length;
        for (int i = 0; i < n; ) {
            int level = 0;
            char ch = chars[i];
            while (i < n && ch == '\t' && ++level >= 0) i++;

            int j = i;
            boolean isDir = true;

            while (j < n && chars[j] != '\n') {
                if (chars[j++] == '.') isDir = false;
            }
            String cur = input.substring(i, j);
            String prev = hashMap.getOrDefault(level - 1, null);
            String path = prev == null ? cur : prev + "/" + cur;
            if (isDir) hashMap.put(level, path);
            else if (ans == null || path.length() > ans.length()) ans = path;
            i = j + 1;
        }
        return ans == null ? 0 : ans.length();

    }
}
