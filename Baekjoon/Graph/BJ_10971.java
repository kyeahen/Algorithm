package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by kyeahen.
 * Title : 10971 외판원 순회2
 * Category : 백트래킹, 외판원 순회
 * Date: 2021/04/23
 */

public class BJ_10971 {

    static int N;
    static int[][] W;
    static boolean[] visited;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            dfs(i, i, 0, 0);

        }

        System.out.println(result);
    }

    public static void dfs(int v, int start, int count, int sum) {

        if (count == N && v == start) {
            result = Math.min(result, sum);
            return;
        }

        for (int i = 0; i < N; i++) {

            if (!visited[i] && W[v][i] != 0) {
                visited[i] = true;
                sum += W[v][i];
                dfs(i, start, count + 1, sum);
                visited[i] = false;
                sum -= W[v][i];
            }
        }
    }

}
