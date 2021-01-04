package Baekjoon;

import java.util.Scanner;

//자와 각도기 - 알고리즘 스터디(공통)
// - dp
public class BJ_2916 {

    static int N, K;
    static int[] A;

    static int[][] dp;
    static int[] ans;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt(); //창영이가 만드는 방법을 알고 있는 각의 수
        K = s.nextInt(); //현우가 외친 각의 개수

        // (1 ≤ N, K ≤ 10)
        A = new int[11];
        dp = new int[11][370]; //dp[i][j] = i번째 인덱스까지 사용했을 때, j각도를 사용할 수 있는지
        ans = new int[370]; //ans[i] = i각도를 만들 수 있는지

        //창영이가 만들 수 있는 각
        for (int i = 0; i < N; i++) {
            A[i] = s.nextInt();
        }

        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 370; j++) {
                dp[i][j] = -1; //-1로 초기화
            }
        }

        sol(0, 0);

        //현우가 외친 각을 창영이가 만들었는지 확인하기
        for (int i = 0; i < K; i++) {
           int b = s.nextInt(); //현우가 외친 각

           if (ans[b] == 1) { //만들었음
               System.out.println("YES");
           } else {
               System.out.println("NO");
           }
        }
    }


    public static void sol(int i, int sum) { //인덱스, 각도

        if (i > N || dp[i][sum] != -1) { //i가 각의 수를 초과하거나 이미 체크한 dp일 때
            return;
        }

        //체크
        dp[i][sum] = 1;
        ans[sum] = 1;

        //새로운 각 만들기 (모든 각도는 360보다 작다.)
        sol(i + 1, (360 + sum + A[i]) % 360); //두 각을 더해서 새로운 각
        sol(i, sum);
        sol(i + 1, (360 + sum - A[i]) % 360); //두 각을 빼서 새로운 각
    }
}
