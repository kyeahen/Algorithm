package Baekjoon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/* - 톱니바퀴 인덱스
        0
    7       1
6               2
    5       3
        4
 */
class Wheel {
    int num; //톱니바퀴 번호
    int dir; //회전 방향

    Wheel(int num, int dir) {
        this.num = num;
        this.dir = dir;
    }
}

//톱니바퀴 (2) - 알고리즘 스터디 (공통)
// - 시뮬레이션
public class BJ_15662 {

    static int T, K;
    static int[][] wheel;
    static boolean[] visited;

    static Queue<Wheel> q = new LinkedList<>(); //회전시켜야하는 톱니바퀴 저장
    static ArrayList<Wheel> list = new ArrayList<>(); //영향권 톱니바퀴 저장

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        T = s.nextInt();
        wheel = new int[T][8];

        for (int i = 0; i < T; i++) {
            String str = s.next();
            for (int j = 0; j < 8; j++) {
                wheel[i][j] = str.charAt(j) - '0';
            }
        }

        K = s.nextInt();

        while (K-- > 0) {
            int num = s.nextInt();
            int dir = s.nextInt();

            q.offer(new Wheel(num - 1, dir));
        }

        while (!q.isEmpty()) {
            list.clear();
            visited = new boolean[T]; //톱니바퀴 방문 체크 배열

            Wheel w = q.poll();
            visited[w.num] = true;
            check(w.num, w.dir); //해당 톱니바퀴로 인해 회전에 영향받는 톱니바퀴 체크

            for (Wheel v : list) { //영향권 톱니바퀴 회전시키기
                rotate(v.num, v.dir);
            }
        }

        int count = 0;
        for (int i = 0; i < T; i++) {
            if (wheel[i][0] == 1) { //12시 방향이 S극이면
                count++;
            }
        }

        System.out.println(count);
    }

    //회전해야하는 톱니바퀴를 체크하는 메소드
    public static void check(int num, int dir) {

        list.add(new Wheel(num, dir)); //회전해야하는 톱니바퀴 추가

        if (num - 1 >= 0) { //왼쪽 톱니바퀴

            //맞닿은 극이 같지 않고, 방문한 톱니바퀴가 아닐 때
            if (wheel[num][6] != wheel[num - 1][2] && !visited[num - 1]) {

                visited[num - 1] = true;
                check(num - 1, dir * -1);
            }
        }

        if (num + 1 < T) { //오른쪽 톱니바퀴

            //맞닿은 극이 같지 않고, 방문한 톱니바퀴가 아닐 때
            if (wheel[num][2] != wheel[num + 1][6] && !visited[num + 1]) {

                visited[num + 1] = true;
                check(num + 1, dir * -1);
            }
        }
    }

    //톱니바퀴 회전 메소드
    public static void rotate(int num, int dir) {

        //wheel 복사
        int[][] temp = new int[T][8];
        for (int i = 0; i < T; i++) {
            for (int j = 0; j < 8; j++) {
                temp[i][j] = wheel[i][j];
            }
        }

        if (dir == 1) { //시계 방향

            for (int i = 0; i < 8; i++) {

                if (i == 0) {
                    wheel[num][0] = temp[num][7];
                } else {
                    wheel[num][i] = temp[num][i - 1];
                }
            }
        } else { //반시계 방향

            for (int i = 0; i < 8; i++) {

                if (i == 7) {
                    wheel[num][7] = temp[num][0];
                } else {
                    wheel[num][i] = temp[num][i + 1];
                }
            }
        }
    }

}
