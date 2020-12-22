package Baekjoon.Greedy;
import java.util.Scanner;

//라면 사기
/*
4
1 2 1 1
wrong : 13
correct : 12
 */
public class BJ_18185 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        int[] A = new int[n];

        for (int i = 0; i < n; i++) {
            A[i] = s.nextInt();
        }

        int ans = 0;
        int count = 0;
        boolean flag = false; //1일 때
        for (int i = 0; i < n; i++) {

            if (A[i] != 0) {

                if (A[i] > 1) {
                    ans += (A[i] - 1) * 3;
                }

                count++;
                flag = true;
            } else {
                flag = false;

                if (count == 1) {
                    ans += 3;
                }
                if (count == 2) {
                    ans += 5;
                }
                if (count == 3) {
                    ans += 7;
                }

                count = 0;
            }
        }

        if (flag) {

            if (count == 1) {
                ans += 3;
            }
            if (count == 2) {
                ans += 5;
            }
            if (count == 3) {
                ans += 7;
            }

        }

        System.out.print(ans);

    }
}
