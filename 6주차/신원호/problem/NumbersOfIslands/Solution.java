package problem.NumbersOfIslands;

/**
 * Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 *
 * Example 1:
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 *
 * Example 2:
 * Input: grid = [
 *   ["0","-","0","0","0"],
 *   ["0","0","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 *
 *
 * 1은 땅이요 0은 물이로다.
 * 그리드 밖은 전부 물이로다
 *
 * 섬은 상하좌우로 연결된 땅으로 이뤄져있다.
 * 섬의 개수를 구하라
 *
 * 2차원 배열을 순회하며 1을 만나면 섬을 방문하고 방문했다고 표시하고 인접한 섬들을 모두 방문하면 섬 카운트 ++
 * 순회하며 이미 방문한 섬들은 방문하지 않는다.
 */
public class Solution {
    int width;
    int height;
    int islandCount;

    public int numIslands(char[][] grid) {
        width = grid.length;
        height = grid[0].length;

        // 2차원 배열을 순회하며
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                // 땅이면
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    islandCount++;
                }
            }
        }

        return islandCount;
    }

    public void bfs(char[][] grid, int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            return;
        }

        if (grid[x][y] == '1') {
            grid[x][y] = '3';
            bfs(grid, x - 1, y);
            bfs(grid, x + 1, y);
            bfs(grid, x, y - 1);
            bfs(grid, x, y + 1);
        }
    }
}
