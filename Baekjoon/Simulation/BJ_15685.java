package Baekjoon.Simulation;

import java.util.ArrayList;
import java.util.Scanner;

//드래곤 커브 - 알고리즘 스터디 (공통)
// - 시뮬레이션
public class BJ_15685 {

    static int N;
    static int[][] map;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();

        map = new int[101][101]; //크기가 100×100인 격자 위에 드래곤 커브

        for (int i = 0; i < N; i++) {
            int x = s.nextInt();
            int y = s.nextInt();
            int d = s.nextInt();
            int g = s.nextInt();

            move(x, y, getDir(d, g));
        }

        System.out.println(getResult());
    }

    //
    public static void move(int x, int y, ArrayList<Integer> dirList) {

        map[x][y] = 1; //좌표 체크

        for (int i = 0; i < dirList.size(); i++) {
            int d = dirList.get(i);

           if (d == 0) { //x좌표가 증가하는 방향 (→)
               x += 1;
           } else if (d == 1) { //y좌표가 감소하는 방향 (↑)
               y -= 1;
           } else if (d == 2) { //x좌표가 감소하는 방향 (←)
               x -= 1;
           } else { //y좌표가 증가하는 방향 (↓)
               y += 1;
           }

           map[x][y] = 1; //새로 이동한 좌표 체크
        }
    }

    //다음 세대 드래곤 커브를 구하는 메소드
    public static ArrayList<Integer> getDir(int d, int g) {
        ArrayList<Integer> dirList = new ArrayList<>(); //드래곤 커브 방향 저장
        dirList.add(d);

        for (int i = 0; i < g; i++) { //g세대까지 방향 구하기

            /*
            0세대 : 0
            1세대 : 0, 1(0+1)
            2세대 : 0 / 1 / 2(1+1), 1(0+1)
            3세대 : 0 / 1 / 2 1 / 2(1+1), 3(2+1), 2(1+1), 1(0+1)
             */
            for (int j = dirList.size() - 1; j >= 0; j--) { //끝점을 기준으로 90도 회전
                int nd = dirList.get(j);

                //반시계 방향으로 90도 회전
                if (nd == 3) {
                    dirList.add(0);
                } else {
                    dirList.add(nd + 1);
                }
            }
        }

        return dirList;
    }

    //결과 출력 메소드
    public static int getResult() {
        int count = 0;

        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {

                // 크기가 1×1인 정사각형의 네 꼭짓점이 모두 드래곤 커브의 일부인 것 카운트
                if (map[i][j] == 1 && map[i + 1][j] == 1 && map[i][j + 1] == 1 && map[i + 1][j + 1] == 1) {
                    count++;
                }
            }
        }

        return count;
    }
}
