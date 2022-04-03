import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class a599findRestaurant {
    public static void main(String[] args) {
        findRestaurant(new String[]{"Shogun","Tapioca Express","Burger King","KFC"},new String[]{
                "KFC","Shogun","Burger King"});
        System.out.println(findRestaurant(new String[]{"Shogun", "Tapioca Express", "Burger King"
                , "KFC"}, new String[]{"Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"}));
    }

    public static String[] findRestaurant(String[] list1, String[] list2) {
        HashMap<String, Integer> map = new HashMap<>();
//        HashMap<String, Integer> map2 = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        int min = Integer.MAX_VALUE;
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                if (i + map.get(list2[i]) < min) {
                    min = i + map.get(list2[i]);
                    ans = new ArrayList<>();
                    ans.add(list2[i]);
                }else if (i + map.get(list2[i]) == min){
                    ans.add(list2[i]);
                }
            }
        }
        String[] strings = new String[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            strings[i] = ans.get(i);
        }


        return strings;
    }
}
