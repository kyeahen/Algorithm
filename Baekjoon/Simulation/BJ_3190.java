package Baekjoon.Simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by kyeahen.
 * Title : 3190 뱀
 * Category : 시뮬레이션
 * Date: 2021/02/08
 */

public class BJ_3190 {

    static class Point {
        int x;
        int y;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int N, K, L;
    static int[][] map;

    //상하좌우
    static int[] dx = {-1, 0, 1, 0}; // 북 동 남 서
    static int[] dy = {0, 1, 0, -1};

    static LinkedList<Point> dir_list = new LinkedList<>(); //뱀의 방향 정보 저장 리스트

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine()); //보드의 크기 (N x N)
        K = Integer.parseInt(br.readLine()); //사과의 개수

        map = new int[N][N];

        //사과의 위치
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[r - 1][c - 1] = -1; //사과
        }

        L = Integer.parseInt(br.readLine()); //뱀의 방향 변환 횟수

        //뱀의 방향 변환 정보
        // "게임 시작 시간으로부터 X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전시킨다는 뜻"
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            String c = st.nextToken();

            // 왼쪽(C) = 0, 오른쪽(D) = 1
            int dir = 0;
            if (c.equals("D")) {
                dir = 1;
            }

            dir_list.add(new Point(x, dir));
        }

        // "게임이 시작할때 뱀은 맨위 맨좌측에 위치하고 뱀의 길이는 1 이다."
        slove(0, 0);
    }

    //사과 = -1 , 뱀 = 8, 빈칸 = 0
    public static void slove(int x, int y) {
        int time = 0;
        int dir = 1; // "뱀은 처음에 오른쪽을 향한다."

        LinkedList<Point> snake_list = new LinkedList<>();
        snake_list.add(new Point(x, y));

        while (true) {
            time++;

            Point head = snake_list.getLast();
            Point tail = snake_list.getFirst();

            int nx = dx[dir] + head.x;
            int ny = dy[dir] + head.y;

            // "벽 또는 자기자신의 몸과 부딪히면 게임이 끝난다."
            if (!isRange(nx, ny) || map[nx][ny] == 8) { break; };

            if (map[nx][ny] == -1) { //사과

                // "그 칸에 있던 사과가 없어지고 꼬리는 움직이지 않는다."
                snake_list.add(new Point(nx, ny));
                map[nx][ny] = 8; //뱀
            }

            if (map[nx][ny] == 0)  { //빈칸
                snake_list.add(new Point(nx, ny));
                map[nx][ny] = 8; //뱀

                // "만약 이동한 칸에 사과가 없다면, 몸길이를 줄여서 꼬리가 위치한 칸을 비워준다."
                map[tail.x][tail.y] = 0;
                snake_list.remove(tail);
            }


            //뱀의 방향 변환 정보가 남아있고, x초에 도달했을 때 (dir_list.x = x초)
            if(!dir_list.isEmpty() && (time == dir_list.getFirst().x)) {

                if (dir_list.getFirst().y == 1) { //오른쪽 'D' (dir_list.y = 회전 방향)

                    if (dir == 3) {
                        dir = 0;
                    } else {
                        dir++;
                    }

                } else { //왼쪽 'L'

                    if (dir == 0) {
                        dir = 3;
                    } else {
                        dir--;
                    }
                }

                dir_list.remove(0); //반영한 방향 변환 정보 삭제
            }
        }

        System.out.println(time);
    }

    //배열 범위 체크
    public static boolean isRange(int x, int y) {
        if (0 <= x && x < N && 0 <= y && y < N) {
            return true;
        }

        return false;
    }

}
