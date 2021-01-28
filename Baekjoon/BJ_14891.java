package Baekjoon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//톱니바퀴
// - 시뮬레이션
public class BJ_14891 {

    static final int WHEEl = 4; //톱니바퀴 개수
    static final int COG = 8; //톱니 개수

    static int K;
    static int[][] map;
    static boolean[] visited;

    static Queue<Wheel> q = new LinkedList<>();
    static ArrayList<Wheel> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        map = new int[WHEEl][COG];

        for (int i = 0; i < WHEEl; i++) {
            String str = s.next();
            for (int j = 0; j < COG; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        K = s.nextInt();

        while (K-- > 0) {
            int num = s.nextInt();
            int dir = s.nextInt();

            q.add(new Wheel(num - 1, dir));
        }

        while (!q.isEmpty()) {
            list.clear();
            visited = new boolean[WHEEl];

            Wheel w = q.poll();
            visited[w.num] = true;
            check(w.num, w.dir);

            for (Wheel v : list) {
                rotate(v.num, v.dir);
            }
        }

        int sum = 0;

        for (int i = 0; i < WHEEl; i++) {
            if (map[i][0] == 1) {
                sum += Math.pow(2, i);
            }
        }

        System.out.println(sum);
    }

    //톱니바퀴 회전 메소드
    public static void rotate(int num, int dir) {

        int[][] temp = new int[WHEEl][COG];
        for (int i = 0; i < WHEEl; i++) {
            for (int j = 0; j < COG; j++) {
                temp[i][j] = map[i][j];
            }
        }

        if (dir == 1) { //시계 방향

            for (int i = 0; i < COG; i++) {

                if (i == 0) {
                    map[num][0] = temp[num][7];
                } else {
                    map[num][i] = temp[num][i - 1];
                }
            }
        } else { //반시계 방향

            for (int i = 0; i < COG; i++) {

                if (i == 7) {
                    map[num][7] = temp[num][0];
                } else {
                    map[num][i] = temp[num][i + 1];
                }
            }
        }
    }

    //회전해야하는 톱니바퀴 찾는 메소드
    public static void check(int num, int dir) {
        list.add(new Wheel(num, dir));

        if (num - 1 >= 0) { //왼쪽 톱니바퀴

            //이미 방문했거나 맞닿은 극이 같으면 건너뛰기
            if (!visited[num - 1] && map[num - 1][2] != map[num][6]) {
                visited[num - 1] = true;
                check(num - 1, dir * -1);
            }
        }

        if (num + 1 < WHEEl) { //오른쪽 톱니바퀴

            //이미 방문했거나 맞닿은 극이 같으면 건너뛰기
            if (!visited[num + 1] && map[num][2] != map[num + 1][6]) {
                visited[num + 1] = true;
                check(num + 1, dir * -1);
            }
        }
    }
}
