package Baekjoon.Resolving;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//DFS와 BFS - 20.09.25
public class BJ_1260 {

    static int N, M, V;
    static int[][] node;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        M = s.nextInt();
        V = s.nextInt();

        node = new int[N + 1][N + 1]; //좌표를 그대로 받아들이기 위해 +1
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            int a = s.nextInt();
            int b = s.nextInt();

            node[a][b] = node[b][a] = 1;
        }
        dfs(V);
        System.out.println();
        Arrays.fill(visited, false); //배열 초기화
        bfs();
    }

    public static void dfs(int v) {
        visited[v] = true;
        System.out.print(v + " ");

        for (int i = 1; i <= N; i++) {
            if (node[v][i] == 1 && !visited[i]) {
                dfs(i);
            }
        }
    }

    public static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(V);
        visited[V] = true;

        while (!q.isEmpty()) {
            int v = q.poll();
            System.out.print(v + " ");

            for (int i = 1; i <= N; i++) {
                if (node[v][i] == 1 && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
    }
}
