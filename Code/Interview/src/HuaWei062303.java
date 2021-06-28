import java.util.*;

public class HuaWei062303 {
    static class Node {
        int begin;
        int end;
        int weight;

        public Node(int begin, int end, int weight) {
            this.begin = begin;
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = Integer.parseInt(scanner.nextLine());
        HashMap<Integer, ArrayList<Node>> listHashMap = new HashMap<>();
        HashMap<Integer, Integer> indegreeHashMap = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            listHashMap.put(i, new ArrayList<Node>());
            indegreeHashMap.put(i, 0);
        }

        int R = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < R; i++) {
            int begin = Integer.parseInt(scanner.next());
            int end = Integer.parseInt(scanner.next());
            int weight = Integer.parseInt(scanner.next());
            listHashMap.get(begin).add(new Node(begin, end, weight));
        }

//        for (int i = 1; i <= N; i++) {
//            for (int j = 0; j < listHashMap.get(i).size(); j++) {
//                System.out.println("begin-> "+listHashMap.get(i).get(j).begin +"  " +"end->"+listHashMap.get(i).get(j).end
//                        +"  "+"weight->"+listHashMap.get(i).get(j).weight);
//            }
//        }
        int[] TopologicalOrder = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < listHashMap.get(i).size(); j++) {
                int endPoint = listHashMap.get(i).get(j).end;
                indegreeHashMap.put(endPoint, indegreeHashMap.get(endPoint) + 1);
            }
        }
        Stack<Integer> stack = new Stack<>();
        for (Map.Entry<Integer, Integer> entry : indegreeHashMap.entrySet()) {
            if (entry.getValue() == 0) {
                stack.push(entry.getKey());
            }
        }
        while(!stack.empty()){

        }

    }

}
