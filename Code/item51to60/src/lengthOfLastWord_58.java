public class lengthOfLastWord_58 {
    public static void main(String[] args) {
        String str = "a ";
        lengthOfLastWord(str);
    }
    public static int lengthOfLastWord(String s) {
        int ans = 0;
        String str = s.trim();
        for(int i = str.length()-1;i >= 0;i--){
            if ( str.charAt(i) == ' '){
                break;
            }
            ans++;
        }
        System.out.println(ans);
        return ans;
    }
}
