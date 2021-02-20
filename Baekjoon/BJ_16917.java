package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Created by kyeahen.
 * Title : 16917 양념 반 후라이드 반
 * Category : 구현
 * Date: 2021/02/15
 */

public class BJ_16917 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        /*
        2(x + y) = x + y
        반반       후라이드 + 양념

        원래는 반반이 더 저렴할 경우에는 반반으로 구매하고, 남은건 무조건 각각 계산했음.
        예제3이 위의 방식의 반례가 됨.
        반반이 저렴한 경우는 개수를 초과해도 반반으로 구매했을 시, 최소가 됨
         */

        int sum = 0;
        if (2 * C < A + B) { //반반이 각각 사는 것보다 더 저렴

            if (X > Y) { //x 남음
                sum += (2 * C) * Y;

                // ! 이 부분을 놓쳤었음
                // - 남은 개수를 반반, 각각 구매 중 더 저렴한 것으로 구매
                int x = X - Y;
                sum += Math.min((2 * C) * x, A * x);

            } else { //y 남음
                sum += (2 * C) * X;

                int y = Y - X;
                sum += Math.min((2 * C) * y,  B * y);
            }

        } else { //따로 사는게 더 저렴
            sum += (A * X) + (B * Y);
        }

        System.out.println(sum);
    }
}
