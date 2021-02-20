package Baekjoon.DP;

import java.util.Scanner;

//가장 큰 정사각형 - 알고리즘 스터디(공통)
// - dp
public class BJ_1915 {

    static int n, m;
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        n = s.nextInt();
        m = s.nextInt();

        map = new int[n + 1][m + 1];
        dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String str = s.next();
            for (int j = 1; j <= m; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        int max = 0; //최대 변
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {

                /*
                a | b
                c | d

                (i, j) = d로 생각한다.
                대각선/위/왼쪽 좌표 값이 1 이상이면 정사각형 만들 수 있는 조건.
                3개 중 가장 작은 값을 기준으로 정사각형을 만들면 된다.
                작은 값을 찾아서 +1을 "정사각형의 변"이 나옴.
                 */
                if (map[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]) + 1;
                    max = Math.max(dp[i][j], max);
                }
            }
        }

        System.out.println(max * max); //넓이
    }

}
