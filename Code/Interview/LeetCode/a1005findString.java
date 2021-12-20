public class a1005findString {
    public static void main(String[] args) {
        System.out.println(findString(new String[]{"at", "", "", "", "ball", "", "", "car", "",
                "", "dad", "", ""}, "ball"));
    }

    public static int findString(String[] words, String s) {
        int left = 0;
        int right = words.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (words[mid].equals(s)) return mid;
            if (words[mid].equals("")) {
                if (words[right].equals(s)) return right;
                right--;
            } else  if (words[mid].compareTo(s) > 0) right = mid - 1;
            else left = mid + 1;
//            while (words[mid].equals("") && mid >= left) {
//                mid--;
//            }
//            if (mid < left) return -1;
////            if (mid == left) {
////                if (words[mid].equals(s)) return mid;
////                else left = mid - 1;
////            }

        }
        return -1;
    }

}
