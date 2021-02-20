package Baekjoon.Simulation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_19236 {

    static class Fish {
        int x;
        int y;
        int direction;
        boolean status;

        public Fish(int x, int y, int direction, boolean status) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.status = status;
        }
    }

    static int[] dx = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
    static int[] dy = { 0, -1, -1, 0, 1, 1, 1, 0, -1 };

    final static int N = 4;
    static int[][] map;
    static Fish[] fish;

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[4][4];
        fish = new Fish[17];

        for (int i = 0; i < N; i++) {

            StringTokenizer s = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int a = Integer.parseInt(s.nextToken());
                int b = Integer.parseInt(s.nextToken());
                map[i][j] = a;
                fish[a] = new Fish(i, j, b, true);
            }
        }

        fish[map[0][0]].status = false; //물고기 먹힘
        int eat = map[0][0]; //먹힌 물고기 번호 저장
        map[0][0] = -1; //상어가 해당 칸 차지
        dfs(0, 0, fish[eat].direction, eat); //상어 좌표(x,y), 상어 방향, 먹은 물고기의 합

        System.out.println(result);
    }

    public static void dfs(int x, int y, int dir, int sum) {

        int[][] tempMap = new int[N][N];
        Fish[] tempFish = new Fish[17];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tempMap[i][j] = map[i][j];
            }
        }

        for (int i = 1; i <= 16; i++) {
            tempFish[i] = fish[i];
        }

        moveFish();

        for (int i = 1; i <= 3; i++) {
            int nx = (dx[dir] + x) * i;
            int ny = (dy[dir] + y) * i;

            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                if (map[nx][ny] != 0) { //물고기가 없는 칸은 이동하지 않음
                    Fish f = fish[map[nx][ny]];
                    int next = map[nx][ny];

                    f.status = false; //물고기가 상어에게 먹힘
                    map[x][y] = 0; //상어가 이동했으므로, 빈칸이 됨
                    map[nx][ny] = -1; //상어가 물고기를 먹고 자리를 차지
                    dfs(nx, ny, f.direction, sum + next);

                    f.status = true;
                    map[x][y] = -1;
                    map[nx][ny] = next;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = tempMap[i][j];
            }
        }

        for (int i = 1; i <= 16; i++) {
            fish[i] = tempFish[i];
        }

        result = Math.max(sum, result);
    }

    public static void moveFish() {

        for (int i = 1; i <= 16; i++) {
            Fish current = fish[i];

            if (!current.status) {
                continue;
            }

            int x = current.x;
            int y = current.y;
            int dir = current.direction;

            int nx = dx[current.direction] + current.x;
            int ny = dy[current.direction] + current.y;

            boolean flag = false;
            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {

                if (map[nx][ny] != -1) { //해당 칸이 상어가 없는 곳이면
                    flag = true;

                    if (map[nx][ny] == 0) { //해당 칸이 빈칸일 때, 칸 이동
                        current.x = nx;
                        current.y = ny;
                        current.direction = dir;

                        map[nx][ny] = map[x][y];
                        map[x][y] = 0;

                    } else { //해당 칸이 다른 물고기가 있는 곳일 때, 서로 칸 교체
                        Fish another = fish[map[nx][ny]];
                        int next = map[nx][ny];

                        current.x = nx;
                        current.y = ny;
                        current.direction = another.direction;

                        another.x = x;
                        another.y = y;
                        another.direction = dir;

                        map[nx][ny] = map[x][y];
                        map[x][y] = next;
                    }
                }
            }

            if (flag == false) {
                int ndir = dir + 1;

                if (ndir == 9) {
                    ndir = 1;
                }

                while (ndir != dir) {

                    int mx = dx[ndir] + x;
                    int my = dx[ndir] + y;

                    if (mx >= 0 && mx < N && my >= 0 && my < N) {

                        if (map[mx][my] == -1) {
                            ndir++;

                            if (ndir == 9) {
                                ndir = 1;
                            }
                            continue;

                        } else {
                            if (map[mx][my] == 0) {
                                current.x = mx;
                                current.y = my;
                                current.direction = ndir;

                                map[mx][my] = map[x][y];
                                map[x][y] = 0;
                                break;

                            } else {
                                Fish another = fish[map[mx][my]];
                                int next = map[mx][my];

                                current.x = mx;
                                current.y = my;
                                current.direction = another.direction;

                                another.x = x;
                                another.y = y;
                                another.direction = dir;

                                map[mx][my] = map[x][y];
                                map[x][y] = next;
                                break;
                            }
                        }
                    }
                }

            }
        }

        print();
    }

    public static void print() {
        for(int i=0; i<4; i++) {
            for(int j=0; j<4; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
