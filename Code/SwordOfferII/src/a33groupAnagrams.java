import java.util.*;

public class a33groupAnagrams {
    public static void main(String[] args) {
        groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> hashMap = new HashMap<>();
        for (String str : strs) {
            // 可以直接转换成字符数组排序,也可以进行数字映射排序.
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String s = new String(arr);

            if (hashMap.containsKey(s)) {
                List<String> temp = hashMap.get(s);
                temp.add(str);
            } else {
                List<String> temp = new ArrayList<>();
                temp.add(str);
                hashMap.put(s, temp);
            }
        }
        List<List<String>> ans = new ArrayList<>();
        for (Map.Entry<String, List<String>> en : hashMap.entrySet()) {
            ans.add(en.getValue());
        }
        return ans;
    }
}
