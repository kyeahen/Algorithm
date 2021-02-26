package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by kyeahen.
 * Title : 1208 부분 수열의 합2
 * Category : 이분탐색, 중간에서 만나기
 * Date: 2021/02/26
 * ref -
 */
public class BJ_1208 {

    static int N, S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //Arrays.sort();
        binary_search(arr, S);
    }

    public static void binary_search(int[] arr, int key) {
        int mid;
        int left = 0;
        int right = arr.length - 1;

        int sum = 0;
        int count = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            sum += arr[mid];

            System.out.println(sum + ", value : " + arr[mid]);
            if (key == sum) {
                count++;
                System.out.println(count);

                break;
            }

            if (key < sum) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
    }
}
