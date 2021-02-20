package Baekjoon.Graph.Search;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//구슬 탈출2 - bfs
public class BJ_13460 {

    static class Node {
        int rX;
        int rY;
        int bX;
        int bY;
        int count = 0;

        public Node(int rX, int rY, int bX, int bY, int count) {
            this.rX = rX;
            this.rY = rY;
            this.bX = bX;
            this.bY = bY;
            this.count = count;
        }

        public Node() {}
    }

    static int N, M; //세로, 가로 길이
    static char[][] map; //보드 정보를 저장하는 배열
    static boolean[][][][] visited; //방문 체크 배열

    //상하좌우
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        M = s.nextInt();

        map = new char[N][M];
        visited = new boolean[N][M][N][M];

        for (int i = 0; i < N; i++) {
            String str = s.next();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        Node n = new Node();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {

                if (map[i][j] == 'R') { //빨간 구슬 좌표 저장
                    n.rX = i;
                    n.rY = j;
                }

                if (map[i][j] == 'B') { //파란 구슬 좌표 저장
                    n.bX = i;
                    n.bY = j;
                }
            }
        }

        bfs(n);
    }

    public static void bfs(Node ball) {
        Queue<Node> q = new LinkedList<>();
        q.add(ball); //큐에 빨간 구슬, 파란 구슬 좌표 삽입

        while (!q.isEmpty()) {
            Node n = q.poll();
            visited[n.rX][n.rY][n.bX][n.bY] = true; //해당 좌표 방문 체크

            //10번 이하로 움직여서 빨간 구슬을 빼낼 수 없으면 -1 출력
            if (n.count >= 10) {
                System.out.println(-1);
                return;
            }

            //해당 위치의 구슬을 기준으로 상하좌우로 굴린다.
            for (int i = 0; i < 4; i++) {
                int nx = n.rX;
                int ny = n.rY;

                //빨간 구슬을 상하좌우 끝까지 이동 (벽을 만나지 않을 때 까지)
                while (map[nx + dx[i]][ny + dy[i]] != '#') {
                    nx += dx[i];
                    ny += dy[i];

                    //이동한 위치가 'O'이면 탐색 종료
                    if (map[nx][ny] == 'O') {
                        break;
                    }
                }

                int mx = n.bX;
                int my = n.bY;

                //파란 구슬을 상하좌우 끝까지 이동 (벽을 만나지 않을 때 까지)
                while (map[mx + dx[i]][my + dy[i]] != '#') {
                    mx += dx[i];
                    my += dy[i];

                    //이동한 위치가 'O'이면 탐색 종료
                    if (map[mx][my] == 'O') {
                        break;
                    }
                }


                /* 파란색 구슬이 'O'에 빠졌으면, 해당 탐색을 멈춘다.
                - 파란 구슬이 구멍에 빠졌기 때문에 이미 실패지만
                  기울이는 동작을 그만하는 것은 더이상 구슬이 움직이지 않을 때 까지이다. */
                if (map[mx][my] == 'O') {
                    continue;
                }

                //빨간색 구슬이 'O'에 빠졌으면, 탐색 종료
               if (map[nx][ny] == 'O') {
                    System.out.println(n.count + 1);
                    return;
                }

                /* 빨간 구슬, 파란 구슬의 위치가 같은데 모두 'O'에 도착하지 못했을 때
                더 움직인 구슬의 dx[i], dy[i]를 빼준다.
                - 빨간 구슬과 파란 구슬을 동시에 같은 칸에 있을 수 없다 */
                if (nx == mx && ny == my) {

                    if (map[nx][ny] != 'O') {
                        int red = Math.abs(nx - n.rX) + Math.abs(ny - n.rY);
                        int blue = Math.abs(mx - n.bX) + Math.abs(my - n.bY);

                        if (red > blue) {
                            nx-= dx[i];
                            ny -= dy[i];
                        } else {
                            mx -= dx[i];
                            my -= dy[i];
                        }
                    }
                }

                //다음 좌표가 방문한적이 없는 좌표이면 큐에 삽입
                if (visited[nx][ny][mx][my] == false) {
                    q.add(new Node(nx, ny, mx, my, n.count + 1));
                }
            }
        }

        System.out.println(-1);
    }
}
