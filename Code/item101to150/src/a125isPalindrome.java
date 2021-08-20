public class a125isPalindrome {
    public static void main(String[] args) {
        String s ="race a car";
        System.out.println(isPalindrome2(s));
    }

    //回文问题的三种解法：
    // 双指针
    // 栈
    // reverse。
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
    //先预处理字符串
    public static boolean isPalindrome2(String s) {
        StringBuffer temp = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch  = s.charAt(i);
            if (Character.isLetterOrDigit(ch)){
                temp.append(Character.toLowerCase(ch));
            }
        }
        //注意这种写法
        // StringBuffer reTemp = new StringBuffer(temp.reverse());
        // 是错误的，需要创建好后再反转，不然创建出来的是原先的值。
        StringBuffer reTemp = new StringBuffer(temp).reverse();
        System.out.println(reTemp);
        System.out.println(temp);

        //需要转换成String类型进行比较
        //今天使用StringBuffer类的equals()方法进行内容比较时发现两个问题

        //同一对象不同内容，怎么比较都是true
        //不同对象相同内容，怎么比较都是false
        return reTemp.toString().equals(temp.toString());
    }
}
