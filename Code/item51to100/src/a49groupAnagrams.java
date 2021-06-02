import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class a49groupAnagrams {
    public static void main(String[] args) {
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams(strs));
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, ArrayList<String>> hashMap = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            ArrayList<String> strings;
            char[] temp = strs[i].toCharArray();
            Arrays.sort(temp);
            //在Java中，每一个对象的toString方法，
            // 都会打印出“类名@他的hashCode值”这样的一个字符串， getClass().getName() + '@' + Integer.toHexString(hashCode())
            // 数组是“[”这个符号C就代表char，后面的就是这个数组的hashCode值，如果是int类型的数组的话，
            // 就会使[I@*****了
            //strings = hashMap.getOrDefault(temp.toString()), new ArrayList<>());
            // 这种写法错误,key 就变成了[C@10f87f48   [C@b4c966a,
            strings = hashMap.getOrDefault(new String(temp), new ArrayList<>());
            strings.add(strs[i]);
            hashMap.put(new String(temp), strings);
        }
        return new ArrayList<>(hashMap.values());
    }
}
