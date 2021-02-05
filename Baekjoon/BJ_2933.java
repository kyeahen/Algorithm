package Baekjoon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//미네랄 - 알고리즘 스터디(공통)
// - bfs

/*
https://jaejin89.tistory.com/16
https://velog.io/@leeinae/Algorithm-%EB%B0%B1%EC%A4%802933-%EB%AF%B8%EB%84%A4%EB%9E%84-java
*/
public class BJ_2933 {

    static int R, C, N;
    static char[][] map;
    static boolean[][] visited;

    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        R = s.nextInt();
        C = s.nextInt();

        map = new char[R][C];

        for (int i = 0; i < R; i++) {
            String str = s.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        N = s.nextInt(); //막대를 던진 횟수
        for (int i = 0; i < N; i++) {
            int h = s.nextInt(); //막대를 던진 높이

            destroy(R - h, i); //미네랄 파괴
            down(); //클러스터 내리기

        }

        //결과 출력
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }

    }

    //미네랄 파괴하기 (막대기가 미네랄을 만나면 파괴된다)
    public static void destroy(int row, int index) {
        if (index % 2 == 0) { //왼쪽 - 창영

            for (int j = 0; j < C; j++) {
                if (map[row][j] == 'x') { //미네랄
                    map[row][j] = '.';
                    break;
                }
            }

        } else { //오른쪽 - 상근

            for (int j = C - 1; j >= 0; j--) {
                if (map[row][j] == 'x') {
                    map[row][j] = '.';
                    break;
                }
            }
        }
    }


    //클러스터 아래로 내리기
    public static void down() {
        visited = new boolean[R][C];

        Queue<Point> q = new LinkedList<>();
        ArrayList<Point> cluster = new ArrayList<>();

        //맨 밑바닥에 붙어있는 클러스터 체크
        int lastRow = R - 1;
        for (int j = 0; j < C; j++) { //열마다 체크

            if (visited[lastRow][j] || map[lastRow][j] == '.') {
                continue;
            }

            //클러스터이면 (미네랄 조각, x)
            visited[lastRow][j] = true;
            q.offer(new Point(lastRow, j));

            while (!q.isEmpty()) {
                Point p = q.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = dx[i] + p.x;
                    int ny = dy[i] + p.y;

                    if (isRange(nx, ny)) {
                        if (visited[nx][ny]) { continue; }

                        //클러스터이면 (미네랄 조각, x)
                        if (map[nx][ny] == 'x') {
                            visited[nx][ny] = true;
                            q.offer(new Point(nx, ny));
                        }
                    }
                }
            }
        }

        //공중에 떠있는 클러스터 체크 (클러스터 배열에 담기)
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {

                // 방문한 곳은 바닥에 붙어있는 클러스터와 붙어있는 클러스터들 (이동시킬 필요가 없는 클러스터)
                if(visited[i][j]) { continue; }

                if (map[i][j] == 'x') { //공중에 있는 클러스터
                    map[i][j] = '.'; //클러스터 위치는 다 내린 후에 다시 반영할 것임
                    cluster.add(new Point(i, j));
                }
            }
        }

        if (cluster.isEmpty()) { //클러스터가 없으면 탐색 종료
            return;
        }

        //공중에 떠있는 클러스터를 얼마나 내릴 수 있는지 체크
        boolean flag = true;
        while (flag) {

            for (Point p : cluster) {
                int nx = p.x + 1; //아래층 체크
                int ny = p.y;

                //아래로 내려갈 수 없는 경우 (범위 밖 or 아래가 클러스터)
                // "클러스터는 다른 클러스터나 땅을 만나기 전까지 게속해서 떨어진다"
                if (!isRange(nx, ny) || map[nx][ny] == 'x') {
                    flag = false;
                    break;
                }
            }

            if (flag){ //내릴 수 있으면 x좌표(행) 증가
                for (Point p : cluster) {
                    p.x++;
                }
            }
        }

        //클러스터 이동 좌표 반영
        for (Point p : cluster) {
            map[p.x][p.y] = 'x';
        }
    }

    public static boolean isRange(int x, int y) {
        if (0 <= x && x < R && 0 <= y && y < C) {
            return true;
        }

        return false;
    }
}
