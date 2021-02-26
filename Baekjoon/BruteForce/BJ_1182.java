package Baekjoon.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by kyeahen.
 * Title : 1182 부분 수열의 합
 * Category : 완전탐색, 백트래킹
 * Date: 2021/02/26
 */

public class BJ_1182 {

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        boolean[] visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            comb(arr, visited, 0, N, i, S);
        }

        System.out.println(result);
    }

    //조합 : 순서x, 중복x
    public static void comb(int[] arr, boolean[] visited, int start, int size, int pick, int s) {
        if (pick == 0) {

            int sum = 0;
            for (int i = 0; i < size; i++) {
                if (visited[i]) {
                    sum += arr[i];
                }
            }

            if (sum == s) {
                result++;
            }

            return;
        }

        for (int i = start; i < size; i++) {
            visited[i] = true;
            comb(arr, visited, i + 1, size, pick - 1, s);
            visited[i] = false;
        }
    }
}
