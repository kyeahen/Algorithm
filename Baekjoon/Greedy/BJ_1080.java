package Baekjoon.Greedy;
import java.util.Scanner;

/**
 * Created by kyeahen.
 * Title : 1080 행렬
 * Category : 그리디
 * Date: 2021/05/26
 * ref - https://squareyun.tistory.com/33
 */

public class BJ_1080 {

    static int N, M;
    static int[][] A, B;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        M = s.nextInt();

        A = new int[N][M];
        B = new int[N][M];

        for (int i = 0; i < N; i++) {
            String str = s.next();
            for (int j = 0; j < M; j++) {
                A[i][j] = str.charAt(j) - '0';
            }
        }

        for (int i = 0; i < N; i++) {
            String str = s.next();
            for (int j = 0; j < M; j++) {
                B[i][j] = str.charAt(j) - '0';
            }
        }

        System.out.println(solution());
    }

    public static int solution() {
        int[][] tempA = copy();

        int cnt = 0;
        for (int i = 0; i <= N - 3; i++) {
            for (int j = 0; j <= M - 3; j++) {

                if (tempA[i][j] != B[i][j]) {
                    tempA = flip(tempA, i, j);
                    cnt++;
                }
            }
        }

        if (check(tempA)) { //A를 B로 바꾸었으면 횟수 반환
            return cnt;
        }

        return -1;
    }

    //3x3 크기의 부분 행렬에 있는 모든 원소 뒤집기
    public static int[][] flip(int[][] A, int x, int y) {
        for (int i = x; i < x + 3; i++) {
            for (int j = y; j < y + 3; j++) {

                if (A[i][j] == 1) { A[i][j] = 0; }
                else { A[i][j] = 1; }
            }
        }

        return A;
    }

    //A, B 배열 동일한지 체크
    public static boolean check(int[][] A) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (A[i][j] != B[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    //배열 복사
    public static int[][] copy() {
        int[][] temp = new int[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                temp[i][j] = A[i][j];
            }
        }

        return temp;
    }
}
