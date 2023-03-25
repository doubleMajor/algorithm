![img_1.png](img_1.png)

~~~java
class Solution {


    public int numIslands(char[][] grid) {
        int count = 0;

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == '1') {
                    search(grid, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    private void search(char[][] grid, int i, int j) {

        if (!(i < 0 || j < 0 || i == grid.length || j == grid[0].length)
                && grid[i][j] == '1') {
            grid[i][j] = '-';
            search(grid, i + 1, j);
            search(grid, i - 1, j);
            search(grid, i, j + 1);
            search(grid, i, j - 1);
        }
    }
}
~~~