package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by kyeahen.
 * Title : 11048 이동하기
 * Category : dp
 * Date: 2021/02/15
 */

public class BJ_11048 {

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][M + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        /*
            r+1 (아래로) : (1, 0)
            c+1 (오른쪽으로) : (0, 1)
            r+1, c+1 (우측 아래 대각선으로) : (1, 1)
         */

        // 반복문 : bottom-up
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                int r1 = map[i - 1][j]; //r+1
                int c1 = map[i][j - 1]; //c+1
                int r1c1 = map[i - 1][j - 1]; //r+1, c+1

                map[i][j] += Math.max(r1, Math.max(c1, r1c1)); //3개 방향 중 최댓값을 저장
            }
        }

        System.out.println(map[N][M]);
    }


}
