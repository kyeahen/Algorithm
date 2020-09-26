package Baekjoon.Resolving;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

//연산자 끼워넣기 - 20.09.26
public class BJ_14888 {

    static ArrayList<Integer> list;
    static int N;
    static int[] num;
    static int[] op;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        num = new int[N];
        op = new int[4];
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            num[i] = s.nextInt();
        }

        for (int i = 0; i < 4; i++) {
            op[i] = s.nextInt();
        }

        dfs(1, num[0]);

        Collections.sort(list); // 최솟값, 최댓값 출력 위해 오름차순 정렬
        System.out.println(list.get(list.size() - 1)); // 최댓값 출력
        System.out.println(list.get(0)); // 최솟값 출력
    }

    public static void dfs(int depth, int sum) {

        for (int i = 0; i < 4; i++) {

            if (op[i] != 0) {
                op[i]--;

                if (i == 0) {
                    dfs(depth + 1, sum + num[depth]);
                } else if (i == 1) {
                    dfs(depth + 1, sum - num[depth]);
                } else if (i == 2) {
                    dfs(depth + 1, sum * num[depth]);
                } else {
                    dfs(depth + 1, sum / num[depth]);
                }

                op[i]++;
            }
        }

        if (depth == N) {
            list.add(sum);
        }
    }
}
