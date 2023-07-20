//import java.util.*;
//
///**
// * 접근 1.
// * 그래프를 이차원 배열로 바꾼다.
// * 이차원 배열을 그래프로 바꾼다.
// * 반환
// *
// * 결과 : 그래프를 이차원 배열로 만드는데 실패. 노드를 반복없이 한 번 씩만 모두 방문하는데 탈출 조건 실패로 인해 탈락...
// */
//
//class Node {
//    public int val;
//    public List<Node> neighbors;
//    public Node() {
//        val = 0;
//        neighbors = new ArrayList<Node>();
//    }
//    public Node(int _val) {
//        val = _val;
//        neighbors = new ArrayList<Node>();
//    }
//    public Node(int _val, ArrayList<Node> _neighbors) {
//        val = _val;
//        neighbors = _neighbors;
//    }
//}
//
//public class Solution2 {
//
//    public Node cloneGraph(Node node) {
//        // 빈 노드인 경우 바로 반환
//        if (node == null) {
//            return null;
//
//            // 인접 노드가 비어 있는 경우 단일 노드 반환
//        } else if (node.neighbors.size() == 0) {
//            return new Node(1);
//
//        } else {
//            // 원래 그래프의 모든 노드를 복제하여 저장할 HashMap을 선언합니다.
//            Map<Node, Node> visited = new HashMap<Node, Node>();
//
//            // DFS로 모든 노드 순회하면서 복사
//            dfs(node, visited);
//
//            return visited.get(node);
//        }
//    }
//
//    // dfs로 방문
//    private void dfs(Node node, Map<Node, Node> visited) {
//        Node clone = new Node(node.val);
//        visited.put(node, clone);
//
//        for (Node neighbor : node.neighbors) {
//
//            // 방문 안했으면 자식 방문
//            if (!visited.containsKey(neighbor)) {
//                dfs(neighbor, visited);
//            }
//
//            // 자식 붙이기
//            visited.get(node).neighbors.add(visited.get(neighbor));
//        }
//    }
//
//    public static void main(String[] args) {
//        Solution2 solution2 = new Solution2();
//
//        Node n1 = new Node(1);
//        Node n2 = new Node(2);
//        Node n3 = new Node(3);
//        Node n4 = new Node(4);
//
//        n1.neighbors = List.of(n2,n4);
//        n2.neighbors = List.of(n1,n3);
//        n3.neighbors = List.of(n2,n4);
//        n4.neighbors = List.of(n1,n3);
//
//
//
//        solution2.cloneGraph(n1);
//    }
//}
