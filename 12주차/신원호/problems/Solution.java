import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/minimum-path-sum/submissions/948251015/
 *
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
 *
 * Note: You can only move either down or right at any point in time.
 *
 * 첫번째 행과 열의 경우에는 아래, 오른쪽 요소가 없기 때문에 다음 요소만 더해줘서 가중치를 구하고
 * 나머지의 경우에는 좌,우로 갔을 때 더 작은 가중치를 더했습니다.
 */
public class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (i == 0 && j == 0) {
                } else if (i == 0) {
                    grid[i][j] += grid[i][j - 1];
                } else if (j == 0) {
                    grid[i][j] += grid[i - 1][j];
                } else {
                    grid[i][j] += Math.min(grid[i - 1][j], grid[i][j - 1]);
                }
            }
        }

        return grid[m - 1][n - 1];
    }
}


/**
 * 배열의 마지막 값이 그래프의 마지막 노드가 될때까지
 * 리스트에 추가하면서
 * 현재 노드가 마지막 노드이면 결과 리스트에 추가하고
 * 그렇지 않으면 이웃한 정점들을 순회하면서 새로운 경로를 생성해서 큐에 추가한다.
 */
class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> resultPath = new ArrayList<>();
        int lastNode = graph.length - 1;

        Queue<List<Integer>> queue = new LinkedList<>();
        List<Integer> startPath = new ArrayList<>();
        startPath.add(0);
        queue.offer(startPath);

        while (!queue.isEmpty()) {
            List<Integer> path = queue.poll();
            int currentNode = path.get(path.size() - 1);

            if (currentNode == lastNode) {
                resultPath.add(path);
                continue;
            }

            for (int neighbor : graph[currentNode]) {
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(neighbor);
                queue.offer(newPath);
            }
        }

        return resultPath;
    }
}