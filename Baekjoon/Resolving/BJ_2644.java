package Baekjoon.Resolving;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//촌수계산(bfs) - 20.10.06
public class BJ_2644 {

    static int n, m;
    static int a, b;
    static int[][] map;
    static boolean[] visited;

    static int[] count;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        n = s.nextInt();
        a = s.nextInt();
        b = s.nextInt();
        m = s.nextInt();

        map = new int[n + 1][n + 1];
        visited = new boolean[n + 1];
        count = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int x = s.nextInt();
            int y = s.nextInt();

            map[x][y] = map[y][x] = 1;
        }

        bfs();

    }

    public static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(a);
        visited[a] = true;

        while (!q.isEmpty()) {
            int v = q.poll();

            for (int i = 1; i <= m; i++) {

                if (map[v][i] == 1 && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                    count[i] = count[v] + 1;
                }
            }
        }

       if (count[b] == 0) {
           System.out.println(-1);
       } else {
           System.out.println(count[b]);
       }
    }
}
