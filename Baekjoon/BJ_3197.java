package Baekjoon;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

/*
매번 모든 빙하를 훑어 녹이는 것이 아니라 -> 시간초과!
빙하를 녹일 때, 안녹는 빙하가 있으면 다음 날에 녹일 큐에 넣어서
 */


//백조의 호수 - 알고리즘 스터디(공통)
// - 시뮬레이션
public class BJ_3197 {

    static int R, C;
    static char[][] map;
    static boolean[][] visited;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static Queue<Point> q = new LinkedList<>(); //기본 큐
    static Queue<Point> waterQ = new LinkedList<>(); //물 큐
    static Queue<Point> nextQ; //다음날 녹을 빙하를 저장하는 큐

    static List<Point> swan = new LinkedList<>();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        R = s.nextInt();
        C = s.nextInt();

        map = new char[R][C];
        visited = new boolean[R][C];

        //. = 물 , X = 빙판, L = 백조
        for (int i = 0; i < R; i++) {
            String str = s.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {

                if (map[i][j] == 'L') { //백조 위치 list에 저장
                    swan.add(new Point(i, j));
                }

                //'.' 하면 안된다 (66%) => 백조 위치(L)까지 큐에 들어가야하나봄
                if (map[i][j] != 'X') {  //빙판이 아니면 waterQ에 저장
                    waterQ.offer(new Point(i, j));
                }
            }
        }

        Point a = swan.get(0); //시작 백조 좌표
        Point b = swan.get(1); //끝 백조 좌표

        //시작 백조 큐에 삽입
        q.offer(a);
        visited[a.x][a.y] = true;

        boolean flag = false;
        int count = 0;
        while (true) {
            nextQ = new LinkedList<>();

            while (!q.isEmpty()) {
                Point p = q.poll();

                if (p.x == b.x && p.y == b.y) { //두마리 백조가 만났을 때
                    flag = true;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nx = dx[i] + p.x;
                    int ny = dy[i] + p.y;

                    if (0 <= nx && nx < R && 0 <= ny && ny < C) {

                        if (visited[nx][ny]) {
                            continue;
                        }

                        visited[nx][ny] = true; //방문 체크

                        // 물에 인접한 빙판으로 다음 날 백조가 탐색할 지역
                        // - 물이 아니기에 현재는 백조가 해당 좌표로 갈 수 없지만, 물에 접하고 있기 때문에 다음날에는 녹는다.
                        if (map[nx][ny] == 'X') {
                            nextQ.offer(new Point(nx, ny));
                            continue;
                        }

                        q.offer(new Point(nx, ny));
                    }
                }
            }

            if (flag) { //두마리 백조가 만났기에 탈출
                break;
            }

            // q를 다음날 탐색할 지역이 담긴 nextQ로 바꾼다.
            // - 다음날에는 녹아서 물이 되기 때문에 백조가 이동가능하다.
            q = nextQ;

            //빙판 녹이기
            int size = waterQ.size();

            for (int j = 0; j < size; j++) {
                Point p = waterQ.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = dx[i] + p.x;
                    int ny = dy[i] + p.y;

                    if (0 <= nx && nx < R && 0 <= ny && ny < C) {

                        // 물에 인접한 얼음을 발견하면 녹이고 다시 큐에 넣는다.
                        if (map[nx][ny] == 'X') {
                            map[nx][ny] = '.';
                            waterQ.offer(new Point(nx, ny));
                        }
                    }
                }
            }

            count++;
        }

        System.out.println(count);
    }

    // "시간초과"
    //백조 만나는지 체크
    public static boolean isMeet() {
        Point a = swan.get(0); //시작
        Point b = swan.get(1); //끝

        q.offer(a); //시작 백조 위치
        visited[a.x][a.y] = true;

        boolean flag = false;
        while (!q.isEmpty()){
            Point p = q.poll();

            if (p.x == b.x && p.y == b.y) {
                flag = true;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;

                if (0 <= nx && nx < R && 0 <= ny && ny < C) {

                    if (visited[nx][ny]) { continue; }

                    if (map[nx][ny] == '.') {
                        q.offer(new Point(nx, ny));
                        visited[nx][ny] = true;
                    }

                    // 물에 인접한 얼음으로 다음 날 백조가 탐색할 지역
                    if (map[nx][ny] == 'X') {
                        nextQ.offer(new Point(nx, ny));
                        continue;
                    }
                }
            }
        }

        return flag;
    }

    //빙판 녹이기
    public static void melt() {
        int size = waterQ.size();

        for (int a = 0; a < size; a++) {
            Point p = waterQ.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;

                if (0 <= nx && nx < R && 0 <= ny && ny < C) {

                    if (visited[nx][ny]) { continue; }

                    // 물에 인접한 얼음을 발견하면 녹이고 다시 큐에 넣는다.
                    if (map[nx][ny] == 'X') {
                        waterQ.offer(new Point(nx, ny));
                        map[nx][ny] = '.';
                    }
                }
            }
        }
    }

}
