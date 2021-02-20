package Baekjoon.Simulation;

import java.util.Scanner;

//배열 돌리기3 - 알고리즘 스터디(공통)
// - 시뮬레이션
public class BJ_16935 {

    static int N, M, R;
    static int[][] map;
    static int[] cal;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        M = s.nextInt();
        R = s.nextInt();

        map = new int[N][M];
        cal = new int[R];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = s.nextInt();
            }
        }

        for (int i = 0; i < R; i++) {
            cal[i] = s.nextInt();
            calculate(cal[i]);
        }

        print(map);
    }

    public static void calculate(int r) {

        int[][] temp;
        int n = map.length;
        int m = map[0].length;

        if (r == 1) { //상하반전 (i, N)
            int mid = n / 2;
            temp = new int[n][m];

            for (int i = 0; i < mid; i++) {
                for (int j = 0; j < m; j++) {
                    temp[i][j] = map[n - i - 1][j];
                    temp[n - i - 1][j] = map[i][j];
                }
            }

        } else if (r == 2) { //좌우반전 (j, M)
            int mid = m / 2;
            temp = new int[n][m];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < mid; j++) {
                    temp[i][j] = map[i][m - j - 1];
                    temp[i][m - j - 1] = map[i][j];
                }
            }

        } else if (r == 3) { //오른쪽 90도 회전 (암기)
            temp = new int[m][n];

            for (int i = 0; i < temp.length; i++) {
                for (int j = 0; j < temp[i].length; j++) {
                    temp[i][j] = map[n - j - 1][i];
                }
            }

        } else if (r == 4) { //왼쪽 90도 회전 (암기)
            temp = new int[m][n];

            for (int i = 0; i < temp.length; i++) {
                for (int j = 0; j < temp[i].length; j++) {
                    temp[i][j] = map[j][m - i - 1];
                }
            }

        } else if (r == 5) { // 1->2 | 2->3 | 3->4 | 4->1
            temp = new int[n][m];
            int rowMid = n / 2;
            int colMid = m / 2;

            //1->2 (1 범위)
            for (int i = 0; i < rowMid; i++) {
                for (int j = 0; j < colMid; j++) { //열만 변함 (오른쪽으로, +)
                    temp[i][j + colMid] = map[i][j];
                }
            }

            //2->3 (2 범위)
            for (int i = 0; i < rowMid; i++) { //행만 변함 (아래로, +)
                for (int j = colMid; j < m; j++) {
                    temp[i + rowMid][j] = map[i][j];
                }
            }

            //3->4 (3 범위)
            for (int i = rowMid; i < n; i++) {
                for (int j = colMid; j < m; j++) { //열만 변함 (왼쪽으로, -)
                    temp[i][j - colMid] = map[i][j];
                }
            }

            //4->1 (4 범위)
            for (int i = rowMid; i < n; i++) { //행만 변함 (위로, -)
                for (int j = 0; j < colMid; j++) {
                    temp[i - rowMid][j] = map[i][j];
                }
            }

        } else { // 1->4 | 4->3 | 3->2 | 2->1
            temp = new int[n][m];
            int rowMid = n / 2;
            int colMid = m / 2;

            //1->4 (1 범위)
            for (int i = 0; i < rowMid; i++) { //행만 변함 (아래로, +)
                for (int j = 0; j < colMid; j++) {
                    temp[i + rowMid][j] = map[i][j];
                }
            }

            //4->3 (4 범위)
            for (int i = rowMid; i < n; i++) {
                for (int j = 0; j < colMid; j++) { //열만 변함 (오른쪽으로, +)
                    temp[i][j + colMid] = map[i][j];
                }
            }

            //3->2 (3 범위)
            for (int i = rowMid; i < n; i++) { //행만 변함 (위쪽으로, -)
                for (int j = colMid; j < m; j++) {
                    temp[i - rowMid][j] = map[i][j];
                }
            }

            //2->1 (2 범위)
            for (int i = 0; i < rowMid; i++) {
                for (int j = colMid; j < m; j++) { //열만 변함 (왼쪽으로, -)
                    temp[i][j - colMid] = map[i][j];
                }
            }

        }

        map = temp; //연산 결과 대입
    }

    public static void print(int[][] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }

        //System.out.println();
    }
}
