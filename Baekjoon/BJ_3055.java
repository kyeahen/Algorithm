package Baekjoon;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//탈출 - bfs
public class BJ_3055 {

    static int R, C; //행, 열
    static char[][] map; //티떱 숲의 지도를 저장하는 배열

    static Queue<Point> hedgehogQ = new LinkedList<>(); //고슴도치 큐
    static Queue<Point> waterQ = new LinkedList<>(); //물 큐

    //상하좌우
    static int[] dx = { 0, 0, -1, 1 };
    static int[] dy = { -1, 1, 0, 0 };

    static boolean flag = false; //비버의 굴로 도착했는지 판별할 변수
    static int result = 0;

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

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {

                if (map[i][j] == 'S') { //해당 좌표가 고슴도치일 때, 큐에 좌표 삽입
                    hedgehogQ.add(new Point(i, j));
                }

                if (map[i][j] == '*') { //해당 좌표가 물일 때, 큐에 좌표 삽입
                    waterQ.add(new Point(i, j));
                }
            }
        }

        bfs();

        if (flag) { //비버의 굴에 도착했을 때
            System.out.print(result);
        } else { //도착하지 못했을 때
            System.out.print("KAKTUS");
        }
    }

    //고슴도치의 이동 - bfs
    public static void bfs() {

        while (!hedgehogQ.isEmpty()) {
            /*
            매분마다 고슴도치가 이동할 수 있듯이 물 또한 매분마다 이동한다.
            먼저 물을 이동시키고 고슴도를 이동시켜야한다.
             */
            spreadWater();
            result++;

            int len = hedgehogQ.size();

            for (int k = 0; k < len; k++) {
                Point p = hedgehogQ.poll();

                for (int i = 0; i < 4; i++) {
                    int nx = dx[i] + p.x;
                    int ny = dy[i] + p.y;

                    if (nx >= 0 && nx < R && ny >= 0 && ny < C) {

                        if (map[nx][ny] == '.' ) { //해당 좌표가 비어있으면
                            map[nx][ny] = 'S'; //고슴도치 이동
                            hedgehogQ.add(new Point(nx, ny)); //고슴도치 좌표 갱신
                        }

                        if (map[nx][ny] == 'D') { //해당 좌표가 비버의 굴이면 탐색 종료
                            flag = true;
                            return ;
                        }
                    }
                }
            }
        }
    }

    //물의 이동 - bfs
    public static void spreadWater() {

        int len = waterQ.size();
        for (int k = 0; k < len; k++) {
            Point p = waterQ.poll();

            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + p.x;
                int ny = dy[i] + p.y;

                if (nx >= 0 && nx < R && ny >= 0 && ny < C) {

                    if (map[nx][ny] == '.' ) { //해당 좌표가 비어있을 때
                        map[nx][ny] = '*'; //물이 차게 된다.
                        waterQ.add(new Point(nx, ny)); //물 좌표 추가
                    }
                }
            }
        }
    }

}
