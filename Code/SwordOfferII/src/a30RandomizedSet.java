import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class a30RandomizedSet {
    public static void main(String[] args) {
        RandomizedSet obj = new RandomizedSet();
        boolean param_1 = obj.insert(0);
        boolean param_6 = obj.insert(1);
        boolean param_2 = obj.remove(0);

        boolean param_3 = obj.insert(2);
        boolean param_5 = obj.remove(1);
        int param_7 = obj.getRandom();
    }
}

class RandomizedSet {

    /**
     * Initialize your data structure here.
     */


    ArrayList<Integer> list;
    HashMap<Integer, Integer> hashMap;

    public RandomizedSet() {
        this.hashMap = new HashMap<>();
        this.list = new ArrayList<>();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (hashMap.containsKey(val)) {
            return false;
        }
        int idx = list.size();
        list.add(idx, val);
        hashMap.put(val, idx);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (hashMap.containsKey(val)) {
            int idx = hashMap.get(val);
            list.set(idx, list.get(list.size() - 1));
            hashMap.replace(list.get(list.size()-1),idx);
            list.remove(list.size() - 1);
            hashMap.remove(val);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        Random rand = new Random();
        int random = rand.nextInt(hashMap.size());
        return list.get(random);
    }
}