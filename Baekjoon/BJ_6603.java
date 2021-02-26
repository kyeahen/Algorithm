package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by kyeahen.
 * Title : 6603 로또
 * Category : 재귀, 백트래킹
 * Date: 2021/02/26
 * ref - https://bcp0109.tistory.com/entry/%EC%A1%B0%ED%95%A9-Combination-Java
 */

public class BJ_6603 {

    //순서 x, 중복 x : 조합
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int k = Integer.parseInt(st.nextToken());
            if (k == 0) {
                break;
            }

            int[] arr = new int[k];
            boolean[] visited = new boolean[k];

            for (int i = 0; i < k; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            comb(arr, visited, 0, k, 6);
            System.out.println();
        }
    }

    //pick :  조합의 크기 (뽑아야하는 갯수)
    public static void comb(int[] arr, boolean[] visited, int start, int size, int pick) {
        if (pick == 0) {

            for (int i = 0; i < size; i++) {
                if (visited[i]) {
                    System.out.print(arr[i] + " ");
                }
            }
            System.out.println();

            return;
        }

        for (int i = start; i < size; i++) {
            visited[i] = true;
            comb(arr, visited, i + 1, size, pick - 1);
            visited[i] = false;
        }
    }
}
