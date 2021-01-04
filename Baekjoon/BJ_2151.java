package Baekjoon;
import java.util.PriorityQueue;
import java.util.Scanner;

//https://soboruya.tistory.com/44
class Mirror implements Comparable<Mirror> {
    int x;
    int y;
    int dir;
    int count;

    public Mirror(int x, int y, int dir, int count) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.count = count;
    }

    @Override
    public int compareTo(Mirror o) {
        return Integer.compare(this.count, o.count);
    }
}
public class BJ_2151 {

    static int N, X, Y;
    static char[][] map;
    static boolean[][][] visited;

    static int[] dx = {0, -1, 0, 1}; //북서남동
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        map = new char[N][N];
        visited = new boolean[N][N][4];

        for (int i = 0; i < N; i++) {
            String str = s.next();

            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);

                if (map[i][j] == '#') { //문이 설치된 곳 (2곳)
                    X = i;
                    Y = j;
                }
            }
        }

        PriorityQueue<Mirror> pq = new PriorityQueue<Mirror>();

        for (int i = 0; i < 4; i++) {
            pq.offer(new Mirror(X, Y, i, 0));
        }

        while (!pq.isEmpty()) {

            Mirror m = pq.poll();

            if (visited[m.x][m.y][m.dir]) {
                continue;
            }

            visited[m.x][m.y][m.dir] = true;

            if (map[m.x][m.y] == '#' && (m.x != X && m.y != Y)) {
                System.out.println(m.count);
                System.exit(0);
            }

            int nx = dx[m.dir] + m.x;
            int ny = dy[m.dir] + m.y;

            if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                if (map[nx][ny] == '!' && map[nx][ny] != '*') { //거울을 설치할 수 있는 위치
                    int ndir = 0;

                    if (m.dir == 0) { //반시계 회전
                        ndir = 3;
                    } else {
                        ndir = m.dir - 1;
                    }
                    pq.offer(new Mirror(nx, ny, ndir, m.count + 1));

                    if (m.dir == 3) { //시계 회전
                        ndir = 0;
                    } else {
                        ndir = m.dir + 1;
                    }
                    pq.offer(new Mirror(nx, ny, ndir, m.count + 1));
                }

                pq.offer(new Mirror(nx, ny, m.dir, m.count));
            }
        }

    }
}
