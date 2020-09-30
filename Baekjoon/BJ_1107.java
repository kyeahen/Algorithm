package Baekjoon;
import java.util.Scanner;

//리모컨 - 완전탐색
public class BJ_1107 {

    static boolean[] broken = new boolean[10];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        for (int i = 0; i < m; i++) {
            int num = sc.nextInt();
            broken[num] = true;
        }

        int ans = Math.abs(n - 100); // + 또는 - 버튼으로만 이동 횟수를 기준

        for (int i = 0; i <= 999999; i++) {
            String current = String.valueOf(i);
            int len = current.length();

            boolean flag = false;
            for (int j = 0; j < len; j++) {
                int v = current.charAt(j) - '0';

                if (broken[v]) { //고장난 번호일 때
                    flag = true;
                    break;
                }
            }

            if (!flag) { //고장나지 않은 번호인 경우

                // 숫자 버튼 횟수 + 이동한 번호와 목표 번호간의 차이
                int count = len + Math.abs(i - n);

                if (ans > count) {
                    ans = count;
                }
            }
        }

        System.out.println(ans);
    }

}
