import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class a352SummaryRanges {
    public static void main(String[] args) {
        SummaryRanges obj = new SummaryRanges();
        obj.addNum(1);
        obj.addNum(3);
        obj.addNum(7);
        obj.addNum(2);
        obj.addNum(6);
        obj.addNum(9);
        obj.addNum(4);
        obj.addNum(10);
        obj.addNum(5);
        int[][] param_2 = obj.getIntervals();
    }
}

class SummaryRanges {
    // 并不需要存储所有值,只需要存储区间
    TreeMap<Integer, Integer> section;

    public SummaryRanges() {
        section = new TreeMap<>();
    }

    public void addNum(int val) {


        if (section.isEmpty()) {
            section.put(val, val);
            return;
        }

        Integer left = 0;
        Integer right = 0;
        Integer preLeft = Integer.MAX_VALUE - 1;
        Integer preRight = Integer.MAX_VALUE - 1;
        for (Map.Entry<Integer, Integer> en : section.entrySet()) {
            left = en.getKey();
            right = en.getValue();
            // 包含
            if (val <= right && val >= left) {
                return;
            }
            //与前一个区间合并
            if (val == left - 1 && val == preRight + 1) {
                section.remove(left);
                section.replace(preLeft, right);
                return;
            }
            //添加在当前的前面
            if (val == left - 1) {
                section.remove(left);
                section.put(val, right);
                return;
            }
            // 添加在前面的后面
            if (val == preRight + 1) {
                section.replace(preLeft, val);
                return;
            }

            if (val < left && val > preRight) {
                section.put(val, val);
                return;
            }
            preLeft = left;
            preRight = right;
        }
        if (val == right + 1) {
            section.replace(left, val);
            return;
        }
        section.put(val, val);

    }

    public int[][] getIntervals() {
        int[][] ans = new int[section.size()][2];
        int i = 0;
        for (Map.Entry<Integer, Integer> en : section.entrySet()) {
            ans[i++] = new int[]{en.getKey(), en.getValue()};
        }
        return ans;
    }
}