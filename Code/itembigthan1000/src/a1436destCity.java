import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class a1436destCity {
    public static void main(String[] args) {

//        [[],["New York","Lima"],["Lima","Sao Paulo"]]
//        List par
//        Arrays.asList(new String[]{"London","New York"})
//        System.out.println(destCity());
    }

    public static String destCity(List<List<String>> paths) {
        HashMap<String, String> hashMap = new HashMap<>();

        Iterator<List<String>> it = paths.iterator();
        String rand = "";
        while (it.hasNext()) {
            List<String> node = it.next();
            String start = node.get(0);
            String end = node.get(1);
            hashMap.put(start, start);
            hashMap.put(end, end);
            rand = start;
        }
        it = paths.iterator();
        while (it.hasNext()) {
            List<String> node = it.next();
            String start = node.get(0);
            String end = node.get(1);
            while (hashMap.get(end) != end) {
                end = hashMap.get(end);
            }
            hashMap.put(start, end);
        }
        return hashMap.get(rand);
    }

}
