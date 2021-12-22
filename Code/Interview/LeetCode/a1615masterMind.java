import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class a1615masterMind {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(masterMind("BGBG", "RGBR")));
        System.out.println(Arrays.toString(masterMind("RGBY", "GGRR")));
    }

    public static int[] masterMind(String solution, String guess) {

        HashMap<Character, Integer> sls = new HashMap<>();
        HashMap<Character, Integer> ges = new HashMap<>();
        int correct = 0;
        for (int i = 0; i < solution.length(); i++) {
            Character ch1 = solution.charAt(i);
            Character ch2 = guess.charAt(i);
            if (ch1 == ch2) {
                correct++;
                continue;
            }
            sls.put(ch1, sls.getOrDefault(ch1, 0) + 1);
            ges.put(ch2, ges.getOrDefault(ch2, 0) + 1);
        }

        int pseudo = 0;
        for (Map.Entry<Character, Integer> en : sls.entrySet()) {
            int right = en.getValue();
            int ge = ges.getOrDefault(en.getKey(),0);
            pseudo += Math.min(right, ge);
        }
        return new int[]{correct, pseudo};


    }
}
