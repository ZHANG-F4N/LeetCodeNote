import java.util.Map;
import java.util.TreeMap;

public class LeetCode1010a3 {
    public static void main(String[] args) {
        StockPrice obj = new StockPrice();
        obj.update(1, 10);
        obj.update(2, 5);
        obj.update(1, 3);
        obj.update(4, 2);
        int param_2 = obj.current();
        int param_3 = obj.maximum();
        int param_4 = obj.minimum();
    }
}

class StockPrice {
    private TreeMap<Integer, Integer> priceLiquid;
    private Integer maxPrice;
    private Integer maxPriceTime;
    private Integer minPrice;
    private Integer minPriceTime;

    public StockPrice() {
        priceLiquid = new TreeMap<>();
        maxPrice = Integer.MIN_VALUE;
        maxPriceTime = -1;
        minPrice = Integer.MAX_VALUE;
        minPriceTime = -1;
    }

    public void update(int timestamp, int price) {
        if (priceLiquid.containsKey(timestamp)) {
            priceLiquid.replace(timestamp, price);

            if (timestamp == maxPriceTime) {
                maxPrice = Integer.MIN_VALUE;
                for (Map.Entry<Integer, Integer> en : priceLiquid.entrySet()) {
                    if (en.getValue() > maxPrice) {
                        maxPrice = en.getValue();
                        maxPriceTime = en.getKey();
                    }
                }
            } else if (timestamp == minPriceTime) {
                minPrice = Integer.MAX_VALUE;
                for (Map.Entry<Integer, Integer> en : priceLiquid.entrySet()) {
                    if (en.getValue() < minPrice) {
                        minPrice = en.getValue();
                        minPriceTime = en.getKey();
                    }
                }
            }else {
                if (price < minPrice) {
                    minPrice = price;
                    minPriceTime = timestamp;
                }
                if (price > maxPrice) {
                    maxPrice = price;
                    maxPriceTime = timestamp;
                }
            }
//            if (timestamp == maxPriceTime || timestamp == minPriceTime) {
//                maxPrice = Integer.MIN_VALUE;
//                minPrice = Integer.MAX_VALUE;
//                for (Map.Entry<Integer, Integer> en : priceLiquid.entrySet()) {
//                    if (en.getValue() > maxPrice) {
//                        maxPrice = en.getValue();
//                        maxPriceTime = en.getKey();
//                    }
//                    if (en.getValue() < minPrice) {
//                        minPrice = en.getValue();
//                        minPriceTime = en.getKey();
//                    }
//                }
//                return;
//            }
        } else {
            priceLiquid.put(timestamp, price);
            if (price < minPrice) {
                minPrice = price;
                minPriceTime = timestamp;
            }
            if (price > maxPrice) {
                maxPrice = price;
                maxPriceTime = timestamp;
            }
        }
    }

    public int current() {
        return priceLiquid.get(priceLiquid.lastKey());
    }

    public int maximum() {
        return maxPrice;
    }

    public int minimum() {
        return minPrice;
    }
}

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */