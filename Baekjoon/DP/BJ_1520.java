package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by kyeahen.
 * Title : 1520 내리막길
 * Category : 그래프 이론, DP
 * Date: 2021/05/04
 */
public class BJ_1520 {

    static int M, N;
    static int[][] map;
    static int[][] dp;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M + 1][N + 1];
        dp = new int[M + 1][N + 1];

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = Integer.MIN_VALUE; //초기화
            }
        }

        System.out.println(dfs(1, 1));
    }

    public static int dfs(int x, int y) {

        if (x == M && y == N) {
            return 1;
        }

        if (dp[x][y] != Integer.MIN_VALUE) {
            return dp[x][y];
        }

        dp[x][y] = 0; //현재 위치에서 끝점까지 도달하는 경로의 개수를 0으로 초기화

        for (int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if (1 <= nx && nx <= M && 1 <= ny && ny <= N) {

                if (map[x][y] > map[nx][ny]) {
                    dp[x][y] += dfs(nx, ny);
                }
            }
        }

        return dp[x][y];
    }

}
