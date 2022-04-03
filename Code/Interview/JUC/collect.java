import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class collect {
    public static void main(String[] args) {
//        ArrayList<String> list = new ArrayList<>();
//        List<String> list = new Vector<>();
//        List<String> list = Collections.synchronizedList(new ArrayList<>());

//        List<String> list = new CopyOnWriteArrayList<>();
//        for (int i = 0; i < 100; i++) {
//            new Thread(() -> {
//                list.add(UUID.randomUUID().toString().substring(0, 8));
//                System.out.println(list);
//            }, String.valueOf(i)).start();
//        }
//        Set<String> set = new HashSet<>();
        Set<String> set = Collections.synchronizedSet(new HashSet<>());
//        Map<String,String> map = Collections.synchronizedMap(new HashMap<String, String>());
        Map<String,String> map = new ConcurrentHashMap<>();
//        Set<String> set = new CopyOnWriteArraySet<>();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
