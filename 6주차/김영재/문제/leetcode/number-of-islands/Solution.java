
class Solution {
    boolean[][] visited;
    int length1, length2;

    public int numIslands(char[][] grid) {
        length1 = grid.length;
        length2 = grid[0].length;
        visited = new boolean[length1][length2];
        int result = 0;
        for (int i = 0; i < length1; i++) {
            for (int j = 0; j < length2; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    isIsland(grid, i, j);
                    result++;
                }
            }
        }
        return result;
    }

    private void isIsland(char[][] grid, int i, int j) {
        visited[i][j] = true;
        if (j > 0 && !visited[i][j - 1] && grid[i][j - 1] == '1') {
            isIsland(grid, i, j - 1);
        }
        if (j < length2 - 1 && !visited[i][j + 1] && grid[i][j + 1] == '1') {
            isIsland(grid, i, j + 1);
        }
        if (i > 0 && !visited[i - 1][j] && grid[i - 1][j] == '1') {
            isIsland(grid, i - 1, j);
        }
        if (i < length1 - 1 && !visited[i + 1][j] && grid[i + 1][j] == '1') {
            isIsland(grid, i + 1, j);
        }
    }

    public static void main(String[] args) {
        System.out.println(new Solution().numIslands(new char[][]{{'1','1','1','1','0'},  {'1','1','0','1','0'},  {'1','1','0','0','0'},  {'0','0','0','0','0'}}));
        System.out.println(new Solution().numIslands(new char[][]{{'1','1','0','0','0'},  {'1','1','0','0','0'},  {'0','0','1','0','0'},  {'0','0','0','1','1'}}));
    }

}