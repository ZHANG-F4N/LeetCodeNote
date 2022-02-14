public class a1189maxNumberOfBalloons {
    public static void main(String[] args) {
        System.out.println(maxNumberOfBalloons("loonbalxballpoon"));
        System.out.println(maxNumberOfBalloons("nlaebolko"));
    }

    public static int maxNumberOfBalloons(String text) {
//        HashMap<Character, Integer> hashMap = new HashMap<>();
        int[] map = new int[26];

        for (char c : text.toCharArray()) {
            map[c - 'a']++;
        }

        // balloon
        int ans = Integer.MAX_VALUE;
        ans  =  Math.min(ans,map['b'-'a']);
        ans  =  Math.min(ans,map['a'-'a']);
        ans  =  Math.min(ans,map['l'-'a']>>1);
        ans  =  Math.min(ans,map['o'-'a']>>1);
        ans  =  Math.min(ans,map['n'-'a']);
        return ans;
    }
}
