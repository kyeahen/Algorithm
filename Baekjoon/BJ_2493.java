package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by kyeahen.
 * Title : 2493 탑
 * Category : 스택
 * Date: 2021/05/27
 */

public class BJ_2493 {

    static class Top {
        int index;
        int height;

        Top(int index, int height) {
            this.index = index;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
        //Scanner 사용 시, 메모리 초과 발생
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solution(N, arr);
    }

    public static void solution(int N, int[] arr) {
        int[] ans = new int[N];

        Stack<Top> stack = new Stack<>();
        stack.push(new Top(0, arr[0]));

        for (int i = 1; i < N; i++) {

            while (!stack.isEmpty()) {

                if (stack.peek().height > arr[i]) { //왼쪽 탑이 자신보다 높으면 수신 가능
                    ans[i] = stack.peek().index + 1;
                    break;
                }

                stack.pop(); //자신보다 낮은 탑이면 삭제 (어차피 자신이 더 높기 때문에 수신 받기에 필요 없음)
            }

            stack.push(new Top(i, arr[i])); //스택에 현재 탑 추가
        }

        for (int i = 0; i < ans.length; i++) {
            System.out.print(ans[i] + " ");
        }
    }
}
