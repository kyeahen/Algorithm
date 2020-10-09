package Programmers.Resolving;

import java.util.LinkedList;
import java.util.Queue;

//네트워크 - 20.10.09
public class P_43162 {

    static boolean[][] visited;

    public static void main(String[] args) {
        int n = 3;
        int[][] com = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        int[][] com2 = {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}};
        System.out.println(solution(n, com2));
    }

    public static int solution(int n, int[][] computers) {
        visited = new boolean[n][n];

        int count = 0;
        for (int i = 0; i < computers.length; i++) {
            if (!visited[i][i]) { //0 ~ 2 탐색
                count += 1;
                bfs(i, computers, n);
                //dfs(i, computers, n);
            }
        }

        return count;
    }

    public static void bfs(int index, int[][] computers, int n) {
        Queue<Integer> q = new LinkedList<>();
        q.add(index);

        while (!q.isEmpty()) {
            int v = q.poll();

            for (int i = 0; i < n; i++) {

                if (computers[v][i] == 1 && !visited[v][i]) {
                    visited[v][i] = visited[i][v] = true;
                    q.add(i);
                }
            }
        }
    }

    public static void dfs(int index, int[][] computers, int n) {

        for (int i = 0; i < n; i++) { //index 네트워크와 연결된 네트워크 찾기

            if (computers[index][i] == 1 && !visited[index][i]) {
                visited[index][i] = visited[i][index] = true;
                dfs(i, computers, n);
            }
        }
    }
}
