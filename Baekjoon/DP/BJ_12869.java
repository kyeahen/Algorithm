package Baekjoon.DP;

import java.util.Scanner;

//뮤탈리스크 - 알고리즘 스터디 (공통)
// dp
public class BJ_12869 {

    static int[][][] dp;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        int[] hp = new int[3];

        dp = new int[61][61][61];

        for (int i = 0; i < n; i++) {
            hp[i] = s.nextInt();
        }

        //-1로 초기화
        for (int i = 0; i < 61; i++) {
            for (int j = 0; j < 61; j++) {
                for (int k = 0; k < 61; k++) {
                    dp[i][j][k] = -1;
                }
            }
        }

        System.out.println(solution(hp[0], hp[1], hp[2]));
    }

    /*
    뮤탈리스크 문제인데 만약 scv 3기 모두 체력 60이라고 하면
    뮤탈리스크는 O(61^3)=O(226,981) 이라는 풀만한 숫자의 갯수가 생기게 된다.
    거기에다가 이미 구해낸 scv 체력별 값을 저장하여 큰 문제에서 작은 문제로 풀 수 있기 때문에 DP문제라고 생각할 수 있다.
     */

    //먼가 탈출하지 못하는 조건이 있는듯...흠..
    public static int solution(int a, int b, int c) {

        //음수가 들어가면 배열 인덱스 에러가 나기 때문에 음수면 0으로 초기화 해주기
        a = Math.max(0, a);
        b = Math.max(0, b);
        c = Math.max(0, c);

        //모든 SCV가 파괴된 경우
        if (a == 0 && b == 0 && c == 0) {
            return 0;
        }

        int res = dp[a][b][c]; //해당 인덱스의 값을 우선 받은 다음 시작
        if (res != -1) { //이미 탐색한 곳이면 dp[a][b][c] 반환
            return res;
        }

        //모든 경우를 돌면서 최소 경우 저장
        res = Integer.MAX_VALUE;
        res = Math.min(res, solution(a - 9, b - 3, c - 1) + 1);
        res = Math.min(res, solution(a - 9, b - 1, c - 3) + 1);

        res = Math.min(res, solution(a - 3, b - 9, c - 1) + 1);
        res = Math.min(res, solution(a - 3, b - 1, c - 9) + 1);

        res = Math.min(res, solution(a - 1, b - 9, c - 3) + 1);
        res = Math.min(res, solution(a - 1, b - 3, c - 9) + 1);

        return res;
    }
}
