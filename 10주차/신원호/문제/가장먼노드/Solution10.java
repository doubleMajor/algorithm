import java.util.*;

/**
 * 1. 그래프를 만들고
 * 2. bfs로 최단거리를 구한다.
 * 3. 거리를 저장해둔다.
 * 4. 최대 거리를 가진 개수 반환
 */
public class Solution10 {

    public static void main(String[] args) {
        int[][] edge = {{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}};

        solution(6, edge);
    }
    public static int solution(int n, int[][] edge) {
        int answer = 0;
        List<List<Integer>> graph = new ArrayList<>();
        List<Boolean> visited = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();

        // 그래프 생성 및 초기화
        for (int i = 0; i <= n ; i++) {
            graph.add(new ArrayList<>());
            visited.add(false);
            distances.add(0);
        }

        for (int i = 0; i < edge.length; i++) {
            int[] node = edge[i];
            graph.get(node[0]).add(node[1]);
            graph.get(node[1]).add(node[0]);
        }

        Queue<Integer> queue = new LinkedList<>();

        queue.offer(1);

        visited.set(1,true);
        distances.set(1,0);

        while (!queue.isEmpty()) {
            Integer number = queue.poll();

            List<Integer> 넘버에_연결된_숫자들 = graph.get(number);

            for (int i = 0; i < 넘버에_연결된_숫자들.size(); i++) {
                Integer 넘버에_연결된_숫자 = 넘버에_연결된_숫자들.get(i);

                if (!visited.get(넘버에_연결된_숫자)) {
                    visited.set(넘버에_연결된_숫자, true);
                    distances.set(넘버에_연결된_숫자, distances.get(number) + 1);
                    queue.offer(넘버에_연결된_숫자);
                }
            }
        }

        int i = distances.stream().max(Integer::compareTo).get().intValue();

        // 최대 거리를 가진 숫자의 개수를 반환
        return (int) distances.stream().filter(integer -> i == integer).count();
    }
}
