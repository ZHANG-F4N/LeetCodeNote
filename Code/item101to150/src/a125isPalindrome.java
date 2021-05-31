public class a125isPalindrome {
    public static void main(String[] args) {
        String s = "race a car";
        System.out.println(isPalindrome(s));
    }

    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        String temp =  s.toLowerCase();
        while (left < right) {
            while (!((temp.charAt(left) >= 'a' && temp.charAt(left) <= 'z') || (temp.charAt(left) >= '0' && temp.charAt(left) <= '9')) ){
                left++;
            }
            while (!((temp.charAt(right) >= 'a' && temp.charAt(right) <= 'z') || (temp.charAt(right) >= '0' && temp.charAt(right) <= '9')) ){
                right--;
            }
            if(left<right && (temp.charAt(left++) != temp.charAt(right--))){
                return false;
            }
        }
        return true;
    }
}
