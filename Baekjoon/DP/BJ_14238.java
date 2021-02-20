package Baekjoon.DP;

import java.util.Scanner;

//출근기록 - 알고리즘 스터디 (공통)
// - dp
public class BJ_14238 {

    static boolean[][][][][] dp;
    static int[] count;
    static char[] result;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        dp = new boolean[51][51][51][3][3]; //[a][b][c][전전날][전날]
        count = new int[3]; //0: A, 1: B, 2: C - 주어진 알파벳 개수
        result = new char[51]; //S 길이 50 이하

        String str = s.next(); //S
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == 'A') {
                count[0]++;
            } else if (c == 'B') {
                count[1]++;
            } else {
                count[2]++;
            }
        }

        if (solution(0, 0, 0, 0, 0)) { //올바른 출근 기록 구했을 때 (A, B, C 다 사용)

            for (int i = 0; i < str.length(); i++) {
                System.out.print(result[i]);
            }
        } else {
            System.out.println(-1);
        }

    }

    //a 개수, b 개수, c 개수, 전전날 알파벳(index), 전날 알파벳(index)
    public static boolean solution(int a, int b, int c, int pprev, int prev) {

        //A,B,C 모두 다 사용했을 때
        if (a == count[0] && b == count[1] && c == count[2]) {
            return true;
        }

        //이미 구했던 값이면 그냥 출력 = -1
        if (dp[a][b][c][pprev][prev]) {
            return false;
        }

        dp[a][b][c][pprev][prev] = true; //값 체크

        //A 사용 가능할 때
        if (a + 1 <= count[0]) {
            result[a + b + c] = 'A';

            if (solution(a + 1, b, c, prev, 0)) { //A 추가 후, 탐색
                return true;
            }
        }

        //B 사용 가능할 때
        if (b + 1 <= count[1]) {
            result[a + b + c] = 'B';

            if (prev != 1) { //이전 문자가 B가 아닐 때

                if (solution(a, b + 1, c, prev, 1)) { //B 추가 후, 탐색
                    return true;
                }
            }
        }

        //C 사용 가능할 때
        if (c + 1 <= count[2]) {
            result[a + b + c] = 'C';

            if (pprev != 2 && prev != 2) { //이전 문자, 전전문자가 C가 아닐 때

                if (solution(a, b, c + 1, prev, 2)) { //c 추가 후, 탐색
                    return true;
                }
            }
        }

        return false;
    }
}
