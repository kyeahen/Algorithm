package Baekjoon.DP;

import java.util.Scanner;

//퇴사2 - 알고리즘 스터디(공통)
// - dp
// https://simyeju.tistory.com/55
// https://yabmoons.tistory.com/337
public class BJ_15486 {

    static int N;
    static int[] dp, T, P;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();

        /*
        크기가 N + 2 인 이유
        - 0이 아닌 1부터 사용하여 일수를 세기 위함 (N + 1)
        - i번째 까지 일한 돈은 (i + 1)번째날에 받기 때문 (N + 2)
         */
        dp = new int[N + 2]; //현재 날짜가 가지는 최대 수익 저장
        T = new int[N + 2]; //상담을 완료하는데 걸리는 기간
        P = new int[N + 2]; //상을 했을 때 받을 수 있는 금액

        for (int i = 1; i <= N; i++) {
            T[i] = s.nextInt();
            P[i] = s.nextInt();
        }

        int max = 0; //얻을 수 있는 최대 이익
        for (int i = 1; i <= N + 1; i++) {
            max = Math.max(max, dp[i]); //최대 금액 갱신

            int day = i + T[i]; // (현재 일수 + 상담 기간) = 상담 완료일
            if (day <= N + 1) { //상담 완료일이 퇴사 기간 내이면
                //dp[day] = max + P[i]; - 예제4) 50

                dp[day] = Math.max(max + P[i], dp[day]); //dp 배열 채우기
                // (이전까지 최대 수입 + 오늘 버는 수입)과 이미 구해진 그날의 수익중 최대값 갱신
            }
        }

        System.out.println(max);
    }
}
