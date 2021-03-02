package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Created by kyeahen.
 * Title : 1208 부분 수열의 합2
 * Category : 이분탐색, 중간에서 만나기
 * Date: 2021/03/02
 * ref - https://kora1492.tistory.com/22
 */
public class BJ_1208 {

    static int N, S;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> A = new ArrayList<>();
        ArrayList<Integer> B = new ArrayList<>();

        //절반으로 나눠서 부분수열의 합을 구한다.
        solution(0, N / 2, 0, A); //앞의 절반 (0 - N/2)
        solution(N / 2, N, 0, B); //뒤의 절반 (N/2 - N)

        //정렬
        Collections.sort(A);
        Collections.sort(B);

        int left = 0;
        int right = B.size() - 1;

        long result = 0;
        while (left < A.size() && right >= 0) {
            int l = A.get(left);
            int r = B.get(right);

            //구하고자 하는 합일 경우
            if (l + r == S) {

                //왼쪽에서 중복되는 값의 값 개수를 카운팅
                long lcnt = 0;
                while (left < A.size() && A.get(left) == l) {
                    left++;
                    lcnt++;
                }

                //오른쪽에서 중복되는 값의 개수를 카운팅
                long rcnt = 0;
                while (right >= 0 && B.get(right) == r) {
                    right--;
                    rcnt++;
                }

                result += lcnt * rcnt;
            }

            //구하고자하는 합보다 큰 경우
            if (l + r > S) {
                right--;
            }

            //구하고자하는 합보다 작은 경우
            if (l + r < S) {
                left++;
            }
        }

        //구하고자 하는 합이 0일 때, 공집합(아무것도 선택하지 않은 값)이 포함되어있기에 -1을 빼준다.
        if (S == 0) { result--; };
        System.out.println(result);
    }

    //부분 수열의 합을 구하는 재귀 함수
    public static void solution(int idx, int end, int sum, ArrayList<Integer> list) {

        if (idx == end) {
            list.add(sum);
            return;
        }

        solution(idx + 1, end, sum + arr[idx], list); //현재 idx 선택
        solution(idx + 1, end, sum, list); //현재 idx 선택 안함
    }


}
