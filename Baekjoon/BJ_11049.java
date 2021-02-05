package Baekjoon;

import java.util.Arrays;
import java.util.Scanner;

//행렬 곱셈 순서 - 알고리즘 스터디(공통)
// - dp
// https://m.blog.naver.com/PostView.nhn?blogId=phj8498&logNo=221340992241&proxyReferer=https:%2F%2Fwww.google.com%2F
// https://it-and-life.tistory.com/151
public class BJ_11049 {

    static int N;
    static int[][] m;
    static int[][] dp;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt(); //행렬의 개수

        m = new int[N][2]; //주어진 행렬
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            m[i][0] = s.nextInt(); //행
            m[i][1] = s.nextInt(); //열

            Arrays.fill(dp[i], Integer.MAX_VALUE); //dp 초기화
        }

        System.out.println(solve(0, N - 1));
    }

    //재귀
    public static int solve(int a, int b) {
        if (a == b) { //같은 행렬이면 x
            return 0;
        }

        if (dp[a][b] != Integer.MAX_VALUE) { //이미 계산된 값일 때, 계산해둔 값 반환
            return dp[a][b];
        }

        //i번째 행렬을 기준으로 좌우 행렬 2개를 곱할 때의 카운트를 구함
        for (int i = a; i < b; i++) {

            // 왼쪽 행렬을 만드는 카운트 + 오른쪽 행렬을 만드는 카운트 + 둘을 곱하는 카운트 (m[i]와  m[i+1]의 행렬곱)
            int temp = solve(a, i) + solve(i + 1, b) + m[a][0] * m[i][1] * m[b][1];
            dp[a][b] = Math.min(dp[a][b], temp); //i번과 j번의 곱셈 횟수가 저장됨
        }

        return dp[a][b];
    }
}
