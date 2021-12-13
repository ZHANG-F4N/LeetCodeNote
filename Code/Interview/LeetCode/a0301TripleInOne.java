public class a0301TripleInOne {
    public static void main(String[] args) {
        TripleInOne obj = new TripleInOne(1);
        obj.push(0, 1);
        obj.push(0, 2);
        obj.push(0, 1);
        int param_2 = obj.pop(0);
        param_2 = obj.pop(0);
        param_2 = obj.pop(0);
        boolean param_4 = obj.isEmpty(0);
    }

    static class TripleInOne {
        int[] arr;
        int[] topIdx;
        int[] borderIdx;
        int[] bottomIdx;

        public TripleInOne(int stackSize) {
            arr = new int[stackSize * 3 + 2];
            topIdx = new int[]{0, stackSize + 1, stackSize * 2 + 2};
            borderIdx = new int[]{stackSize, stackSize * 2 + 1, stackSize * 3 + 2};
            bottomIdx = new int[]{0, stackSize + 1, stackSize * 2 + 2};
        }

        public void push(int stackNum, int value) {
            if (topIdx[stackNum] == borderIdx[stackNum]) return;
            arr[topIdx[stackNum]++] = value;
        }

        public int pop(int stackNum) {
            if (topIdx[stackNum] == bottomIdx[stackNum]) return -1;
            return arr[--topIdx[stackNum]];
        }

        public int peek(int stackNum) {
            if (topIdx[stackNum] == bottomIdx[stackNum]) return -1;
            return arr[topIdx[stackNum] - 1];

        }

        public boolean isEmpty(int stackNum) {
            return topIdx[stackNum] == bottomIdx[stackNum];
        }
    }
}
