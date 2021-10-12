public class a273numberToWords {
    public static void main(String[] args) {

    }

    public static String numberToWords(int num) {
        String[] singles = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        String[] thousands = {"", "Thousand", "Million", "Billion"};

        if (num == 0) {
            return "zero";
        }

        int thousand = 1000;
        int million = 1000000;
        int billion = 1000000000;

        StringBuilder ans = new StringBuilder();
        char[] arr = String.valueOf(num).toCharArray();


        int N = arr.length;





        return ans.toString();
    }
}
