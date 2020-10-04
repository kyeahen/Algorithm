package Baekjoon;
import java.util.Arrays;
import java.util.Scanner;

//커플만들기 - dp
public class BJ_1727 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt(); //남자
        int m = s.nextInt(); //여자

        int[][] dp = new int[n + 1][m + 1];
        int[] man = new int[n];
        int[] woman = new int[m];

        for (int i = 0; i < n; i++) {
            man[i] = s.nextInt();
        }

        for (int i = 0; i < m; i++) {
            woman[i] = s.nextInt();
        }

        Arrays.sort(man);
        Arrays.sort(woman);

        //맨 처음 값은 현재 성격 차이 값만 저장되도록 dp[0][0]은 0값 유지
        for (int i = 1; i <= man.length; i++) {
            for (int j = 1; j <= woman.length; j++) {
                //이전 값 + 현재 성격 차이 값
                dp[i][j] = dp[i - 1][j - 1] + Math.abs(man[i - 1] - woman[j - 1]);

                if (i > j) { //남자가 여자보다 많을 때
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j]);
                }

                if (i < j) { //여자가 남자보다 많을 때
                    dp[i][j] = Math.min(dp[i][j], dp[i][j - 1]);
                }

            }
        }

        System.out.println(dp[n][m]);
    }
}
