package Baekjoon.DP;
import java.util.Scanner;

//1,2,3 더하기 - 알고리즘 스터디(공통)
// - dp
public class BJ_9095 {

    static int[] dp = new int[11]; // "n은 양수이며 11보다 작다."

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int t = s.nextInt();

        //bottom-up : 반복문 | top-down : 재귀
        for (int i = 0; i < t; i++) {
            int n = s.nextInt();
            dp[0] = 1; //n이 0으로 들어올 경우를 대비
            dp[1] = 1; //1을 만들 수 있는 경우, (1) => 1개
            dp[2] = 2; //2를 만들 수 있는 경우, (1 + 1), (2) => 2개
            dp[3] = 4; //3을 만들 수 있는 경우, (1 + 1 + 1), (2 + 1), (1 + 2), (3) => 4개

            /* ex) arr[4] = arr[3] + arr[2] + arr[1]
            - [j - 1], 3 = +1을 하면 4를 만들 수 있다.
            - [j - 2], 2 = +2를 하면 4를 만들 수 있다.
            - [j - 1], 1 = +3을 하면 4를 만들 수 있다.

            => 위의 경우가 1,2,3을 통해 만들 수 있는 총 가짓수이기 때문에 모두 더해주면 결과값이 나온다.
             */
            for (int j = 4; j <= n; j++) {
                dp[j] = dp[j - 1] + dp[j - 2] + dp[j - 3];
            }

            System.out.println(dp[n]);
        }
    }
}
