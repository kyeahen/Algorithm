package Baekjoon;
import java.util.Scanner;

// 바이러스
public class BJ_2606 {

    static int computers, link;
    static int[][] map;
    static int[] visted;
    static int count = 0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        computers = s.nextInt();
        link = s.nextInt();

        map = new int[computers + 1][computers + 1];
        visted = new int[computers + 1];

        for (int i = 0; i < link; i++) {
            int a = s.nextInt();
            int b = s.nextInt();

            map[a][b] = 1;
            map[b][a] = 1;
        }

        dfs(1);
        System.out.print(count);
    }

    public static void dfs(int start) {
        visted[start] = 1;

        for (int i = 1; i <= computers; i++) {
            if (map[start][i] == 1 && visted[i] == 0) {
                dfs(i);
                count++;
            }
        }
    }
}
