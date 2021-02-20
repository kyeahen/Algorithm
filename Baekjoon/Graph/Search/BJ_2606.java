package Baekjoon.Graph.Search;
import java.util.Scanner;

// 바이러스 - dfs
public class BJ_2606 {

    static int computers, link;
    static int[][] map;
    static int[] visted;
    static int count = 0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        computers = s.nextInt(); //컴퓨터의 수
        link = s.nextInt(); //네트워크 상에서 직접 연결되어 있는 컴퓨터 쌍의 수

        map = new int[computers + 1][computers + 1]; // 컴퓨터 간의 연결을 나타내기 위한 배열
        visted = new int[computers + 1]; // 컴퓨터 방문 여부 체크 배열

        for (int i = 0; i < link; i++) {
            int a = s.nextInt();
            int b = s.nextInt();

            //서로 연결되어 있기에 1을 삽입
            map[a][b] = 1;
            map[b][a] = 1;
        }

        dfs(1); //dfs 탐색
        System.out.print(count); //바이러스에 걸리게 되는 컴퓨터 수 출력
    }

    public static void dfs(int start) {
        visted[start] = 1; //방문한 컴퓨터 -> 1

        for (int i = 1; i <= computers; i++) {

            if (map[start][i] == 1 && visted[i] == 0) { //연결된 컴퓨터이면서 방문하지 않은 컴퓨터
                dfs(i); //dfs 탐색
                count++; //바이러스에 걸리게 되는 컴퓨터 증가
            }
        }
    }
}
