import java.util.*;

public class a884uncommonFromSentences {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(uncommonFromSentences("this  apple is sweet", "this " +
                "apple is sour")));
    }

    public static String[] uncommonFromSentences(String s1, String s2) {
        String[] arr1 = s1.split("\\s+");
        String[] arr2 = s2.split("\\s+");


        HashMap<String, Integer> hashMap = new HashMap<>();

        for (String s : arr1) {
            hashMap.put(s, hashMap.getOrDefault(s, 0) + 1);
        }
        for (String s : arr2) {
            hashMap.put(s, hashMap.getOrDefault(s, 0) + 1);
        }
        List<String> tmp1 = new ArrayList<>();
        for(String s:hashMap.keySet()){
            if(hashMap.get(s) == 1){
                tmp1.add(s);
            }
        }

        String[] ans = new String[tmp1.size()];
        for(int x = 0; x < ans.length; x++){
            ans[x] = tmp1.get(x);
        }
        return ans;
    }
}
