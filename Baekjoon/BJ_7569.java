package Baekjoon;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point_3D {
    int x;
    int y;
    int z;

    Point_3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

//토마토 - bfs
public class BJ_7569 {

    static int M, N, H;
    static int[][][] totamtoes;

    //상하좌우 위아래
    static int[] dx = { -1, 0, 1, 0, 0, 0 };
    static int[] dy = { 0, 1, 0, -1, 0, 0 };
    static int[] dz = { 0, 0, 0, 0, -1, 1 };

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        M = s.nextInt(); //가로 칸의 수 - x
        N = s.nextInt(); //세로 칸의 수 - y
        H = s.nextInt(); //쌓아 올려지는 상자의 수 (높이) - z

        totamtoes = new int[H][N][M]; //상자에 저장된 토마토들의 정보

        for (int i = 0; i < H; i++) { // -z
            for (int j = 0; j < N; j++) { // -y
                for (int k = 0; k < M; k++) { // -x
                    totamtoes[i][j][k] = s.nextInt();
                }
            }
        }

        bfs();
    }

    public static void bfs() {
        Queue<Point_3D> q = new LinkedList<>();

        for (int i = 0; i < H; i++) { // -z
            for (int j = 0; j < N; j++) { // -y
                for (int k = 0; k < M; k++) { // -x

                    if (totamtoes[i][j][k] == 1) { //익은 토마토이면 큐에 삽입
                        q.add(new Point_3D(k, j, i));
                    }
                }
            }
        }

        while (!q.isEmpty()) {
            Point_3D p = q.poll();
            int x = p.x;
            int y = p.y;
            int z = p.z;

            //해당 좌표를 기준으로 상하좌우 위아래 탐색
            for (int i = 0; i < 6; i++) {

                int nx = dx[i] + x;
                int ny = dy[i] + y;
                int nz = dz[i] + z;

                if (nx >= 0 && nx < M && ny >= 0 && ny < N && nz >= 0 && nz < H) {

                    if (totamtoes[nz][ny][nx] == 0) { //해당 좌표가 익지 않은 토마토일 때
                        q.add(new Point_3D(nx, ny, nz)); //해당 토마토가 익기 때문에 큐에 삽입
                        totamtoes[nz][ny][nx] = totamtoes[z][y][x] + 1; //일수 증가
                    }
                }
            }
        }

        print();

        int day = 0;
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {

                    if (totamtoes[i][j][k] == 0) { //익지 않은 토마토가 존재하면
                        System.out.println(-1); //-1 반환
                        return;
                    }

                    //전체 토마토 상자를 탐색하면서 최소 일수(최대값)을 찾는다.
                    day = Math.max(day, totamtoes[i][j][k]);
                }
            }
        }

        //시작 일수가 1(익은 토마토 = 1)에서 시작했기 때문에 -1을 해주면 최소 일수가 나온다.
        System.out.println(day - 1);
     }

    public static void print() {
        for (int i = 0; i < H; i++) { // -z
            for (int j = 0; j < N; j++) { // -y
                for (int k = 0; k < M; k++) { // -x
                    System.out.print(totamtoes[i][j][k] + " ");
                }
                System.out.println();
            }
        }
    }

}
