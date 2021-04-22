package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by kyeahen.
 * Title : 2110 공유기 설치
 * Category : 이분 탐색, 매개 변수 탐색
 * Date: 2021/04/08
 * ref - https://devlog-wjdrbs96.tistory.com/267
 */
public class BJ_2110 {

    static int N, C;
    static int[] points;

    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); //집의 개수
        C = Integer.parseInt(st.nextToken()); //공유기의 개수

        points = new int[N];

        //집의 좌표를 나타대는 x
        for (int i = 0; i < N; i++) {
            points[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(points); //좌표 오름차순 정렬

        /*
        집의 좌표를 오름차순으로 정렬하여 일직선 상에 순서대로 배치한다.
        두 공유기 간의 거리는 최소는 1이고, (=> 거리는 무조건 1 이상 차이 나기 때문!)
        최대는 (집 좌표 마지막 값 - 첫번째 값)이다.
        이 구간의 값을 이분탐색으로 범위를 줄여가면서
        가장 인접한 두 공유기 사이의 최대 거리를 구한다.
         */

        int left = 1;
        int right = points[N - 1] - points[0];

        while (left <= right) { //최대 간격을 이분법으로 줄여나감
            int mid = (left + right) / 2; //공유기 간격 기준

            int start = points[0]; //첫 위치에 설치
            int count = 1; //공유기 설치 개수

            for (int i = 0; i < N; i++) {
                int dist = points[i] - start; //집마다 거리 체크

                if (dist >= mid) { //공유기 설치 가능한지 체크
                    count++;
                    start = points[i]; //설치했으면 해당 집부터 다시 간격 체크
                }
            }

            //- 공유기를 C개 이상 설치할 수 있는지
            if (count >= C) { //개수 이상 : 간격을 넓혀서 공유기 설치할 수 있는지 확인
                left = mid + 1;
                result = mid;
            } else { //개수 부족 : 간격을 줄여서 공유기 설치할 수 있는지 확인
                right = mid - 1;
            }
        }

        System.out.println(result);
    }
}
