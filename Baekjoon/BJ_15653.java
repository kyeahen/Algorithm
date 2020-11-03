package Baekjoon;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//구슬탈출4 - 알고리즘 스터디 (공통)
public class BJ_15653 {

    static int N, M;
    static char[][] map;
    static boolean[][][][] visited;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

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

                if (map[i][j] == 'R') {
                    n.rX = i;
                    n.rY = j;
                }

                if (map[i][j] == 'B') {
                    n.bX = i;
                    n.bY = j;
                }
            }
        }

        bfs(n);
    }

    public static void bfs(Node marble) {
        Queue<Node> q = new LinkedList<>();
        q.add(marble);

        while (!q.isEmpty()) {
            Node n = q.poll();
            visited[n.rX][n.rY][n.bX][n.bY] = true;

            for (int i = 0; i < 4; i++) {
                int nrX = n.rX;
                int nrY = n.rY;

                //빨간 구슬 이동
                while (map[nrX + dx[i]][nrY + dy[i]] != '#') {
                    nrX += dx[i];
                    nrY += dy[i];

                    //이동한 위치가 'O'이면 탐색 종료
                    if (map[nrX][nrY] == 'O') {
                        break;
                    }
                }

                int nbX = n.bX;
                int nbY = n.bY;

                //파란 구슬 이동
                while (map[nbX + dx[i]][nbY + dy[i]] != '#') {
                    nbX += dx[i];
                    nbY += dy[i];

                    //이동한 위치가 'O'이면 탐색 종료
                    if (map[nbX][nbY] == 'O') {
                        break;
                    }
                }

                /* 파란색 구슬이 'O'에 빠졌으면, 해당 탐색을 멈춘다.

                  - 파란 구슬이 구멍에 빠졌기 때문에 이미 실패지만
                    기울이는 동작을 그만하는 것은 더이상 구슬이 움직이지 않을 때 까지이다. */
                if (map[nbX][nbY] == 'O') {
                    continue;
                }

                //빨간색 구슬이 'O'에 빠졌으면, 탐색 종료
                if (map[nrX][nrY] == 'O') {
                    System.out.print(n.count + 1);
                    return;
                }

                /* 빨간 구슬, 파란 구슬의 위치가 같은데 모두 'O'에 도착하지 못했을 때
                   더 움직인 구슬의 dx[i], dy[i]를 빼준다.

                  - 빨간 구슬과 파란 구슬을 동시에 같은 칸에 있을 수 없다. */
                if (nrX == nbX && nrY == nbY) {

                    if (map[nrX][nrY] != 'O') {
                        int red = Math.abs(nrX - n.rX) + Math.abs(nrY - n.rY);
                        int blue = Math.abs(nbX - n.bX) + Math.abs(nbY - n.bY);

                        if (red > blue) {
                            nrX-= dx[i];
                            nrY -= dy[i];
                        } else {
                            nbX -= dx[i];
                            nbY -= dy[i];
                        }
                    }
                }

                //다음 좌표가 방문한적이 없는 좌표이면 큐에 삽입
                if (visited[nrX][nrY][nbX][nbY] == false) {
                    q.add(new Node(nrX, nrY, nbX, nbY, n.count + 1));
                }
            }
        }

        //어떻게 움직여도 빨간 구슬을 구멍을 통해 빼낼 수 없으면 -1을 출력
        System.out.println(-1);
    }
}
