package Baekjoon.DP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by kyeahen.
 * Title : 2096 내려가기
 * Category : DP
 * Date: 2021/05/20
 */

public class BJ_2096 {

    static int N;
    static int[][] dpMax, dpMin, arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1][3 + 1];

        dpMax = new int[N + 1][3 + 1];
        dpMin = new int[N + 1][3 + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            dpMax[i][1] += Math.max(dpMax[i - 1][1], dpMax[i - 1][2]) + arr[i][1]; //1, 2
            dpMax[i][2] += Math.max(Math.max(dpMax[i - 1][1], dpMax[i - 1][2]), dpMax[i - 1][3]) + arr[i][2]; //1, 2, 3
            dpMax[i][3] = Math.max(dpMax[i - 1][2], dpMax[i - 1][3]) + arr[i][3]; //2, 3

            dpMin[i][1] += Math.min(dpMin[i - 1][1], dpMin[i - 1][2]) + arr[i][1]; //1, 2
            dpMin[i][2] += Math.min(Math.min(dpMin[i - 1][1], dpMin[i - 1][2]), dpMin[i - 1][3]) + arr[i][2]; //1, 2, 3
            dpMin[i][3] += Math.min(dpMin[i - 1][2], dpMin[i - 1][3]) + arr[i][3]; //2, 3
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= 3; i++) {
            max = Math.max(max, dpMax[N][i]);
            min = Math.min(min, dpMin[N][i]);
        }

        System.out.println(max + " " + min);
    }


}
