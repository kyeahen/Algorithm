package Baekjoon;

import java.util.Arrays;
import java.util.Scanner;

//풀이 실패
//큐빙 - 알고리즘 스터디(공통)
// - 시뮬레이션

//https://cceeun.tistory.com/54
//https://hoho325.tistory.com/199
public class BJ_5373 {

    static int T, N;
    static char[][] up, down, front, back, left, right;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        T = sc.nextInt();

        up = new char[3][3];
        down = new char[3][3];
        front = new char[3][3];
        back = new char[3][3];
        left = new char[3][3];
        right = new char[3][3];

        while (T-- > 0) {
            fillCube();
            N = sc.nextInt();

            for (int i = 0; i < N; i++) {
                String str = sc.next();
                char color = str.charAt(0);
                char dir = str.charAt(1);

                if (dir == '+') { //시계 방향
                    rotate(color);
                } else { //반시계 방향

                    //시계 방향 3번 돌린 것과 같음
                    for (int j = 0; j < 3; j++) {
                        rotate(color);
                    }
                }
            }

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.println(up[i][j]);
                }
                System.out.println();
            }
        }
    }

    public static void fillCube() {
        for (char[] c : up) {
            Arrays.fill(c, 'w');
        }

        for (char[] c : down) {
            Arrays.fill(c, 'y');
        }

        for (char[] c : front) {
            Arrays.fill(c, 'r');
        }

        for (char[] c : back) {
            Arrays.fill(c, 'o');
        }

        for (char[] c : left) {
            Arrays.fill(c, 'g');
        }

        for (char[] c : right) {
            Arrays.fill(c, 'b');
        }
    }


    public static void rotate(char dir) {

        if (dir == 'U') {
            up = selfRotate(up); //90도 회전

            for (int i = 0; i < 3; i++) {
                char temp = front[0][2 - i];
                front[0][2 - i] = right[0][i];
                right[0][i] = back[0][i];
                back[0][i] = left[0][2 - i];
                left[0][2 - i] = temp;
            }

        } else if (dir == 'D') {
            down = selfRotate(down); //90도 회전
            down = selfRotate(down);
            down = selfRotate(down);

            for (int i = 0; i < 3; i++) {
                char temp = back[2][2 - i];
                back[2][2 - i] = right[2][2 - i];
                right[2][2 - i] = front[2][i];
                front[2][i] = left[2][i];
                left[2][i] = temp;
            }

        } else if (dir == 'F') {
            front = selfRotate(front); //90도 회전

            for (int i = 0; i < 3; i++) {
                char temp = left[2 - i][2];
                left[2 - i][2] = down[2][2 - i];
                down[2][2 - i] = right[i][2];
                right[i][2] = up[2][i];
                up[2][i] = temp;
            }
        } else if (dir == 'B') {
            back = selfRotate(back); //90도 회전
            back = selfRotate(back);
            back = selfRotate(back);

            for (int i = 0; i < 3; i++) {
                char temp = right[2 - i][0];
                right[2 - i][0] = down[0][i];
                down[0][i] = left[i][0];
                left[i][0] = up[0][2 - i];
                up[0][2 - i] = temp;
            }
        } else if (dir == 'L') {
            left = selfRotate(left); //90도 회전

            for (int i = 0; i < 3; i++) {
                char temp = back[2 - i][0];
                back[2 - i][0] = down[2 - i][0];
                down[2 - i][0] = front[i][0];
                front[i][0] = up[i][0];
                up[i][0] = temp;
            }
        } else { // R
            right = selfRotate(right); //90도 회전
            right = selfRotate(right);
            right = selfRotate(right);

            for (int i = 0; i < 3; i++) {
                char temp = down[i][2];
                down[i][2] = back[i][2];
                back[i][2] = up[2 - i][2];
                up[2- i][2] = front[2 - i][2];
                up[2 - i][2] = temp;
            }
        }
    }

    public static char[][] selfRotate(char[][] arr) {
        char[][] temp = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                temp[i][j] = arr[2 - j][i];
            }
        }

        return temp;
    }
}
