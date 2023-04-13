import java.util.LinkedList;
import java.util.Queue;

/**
 * nxm 2차원 배열에 벽과 길이 있고, 벽은 갈 수 없고, 길은 갈 수 있다.
 * 0은 벽이라 갈 수 없고, 1은 길이다.
 *
 * 캐릭터는 1,1에 있고 적은 n,m에 있다.
 *
 * 최단 경로를 칸 수를 구하고 못 가면 -1을 반환하라
 *
 *
 * 접근 1.
 *
 * 최단거리를 구해야 하니까 BFS로 동서남북을 가는데 벽을 만나거나 못 가는 곳이면 반환한다.
 */
public class ShortestWay {

    static int[] dx = {0, 0, -1, 1}; // direction vectors for BFS
    static int[] dy = {-1, 1, 0, 0};

    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;

        boolean[][] visited = new boolean[n][m];
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, 1));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();

            if (p.x == n - 1 && p.y == m - 1) {
                return p.dist;
            }

            for (int i = 0; i < 4; i++) { // try all four directions
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue; // out of bounds
                if (visited[nx][ny] || maps[nx][ny] == 0) continue; // already visited or wall

                q.add(new Point(nx, ny, p.dist + 1));

                visited[nx][ny] = true;
            }
        }

        return -1;
    }

    static class Point {
        int x, y, dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

}
