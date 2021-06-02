
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class a412FizzBuzz {
    public static void main(String[] args) {
        System.out.println( fizzBuzz(15).toArray().toString());
    }

    public static List<String> fizzBuzz(int n) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(3, "Fizz");
        hashMap.put(5, "Buzz");
        ArrayList<String> strings = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            String ans = "";
            for (int val : hashMap.keySet()
            ) {
                if (i % val == 0) {
                    ans += hashMap.get(val);

                }
            }
            if (ans.equals("")){
                strings.add(Integer.toString(i));
            }else {
                strings.add(ans);
            }
        }
        return strings;
    }
}
