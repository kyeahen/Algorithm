package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by kyeahen.
 * Title : 10451 순열 사이클
 * Category : 그래프 탐색, 순열 사이클 분할
 * Date: 2021/04/23
 */
public class BJ_10451 {

    static int T, N;
    static int[][] node;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());
            node = new int[N + 1][N + 1];
            visited = new boolean[N + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int a = Integer.parseInt(st.nextToken());
                node[i][a] = 1; //방향 그래프
            }

            int count = 0;
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    dfs(i);
                    count++;
                }
            }
            System.out.println(count);
        }

    }

    public static void dfs(int v) {
        visited[v] = true;

        for (int i = 1; i <= N; i++) {
            if (visited[i]) { continue; }
            if (node[v][i] == 1) {
                dfs(i);
            }
        }
    }

}
