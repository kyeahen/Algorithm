package Baekjoon;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/**
 * Created by kyeahen.
 * Title : 2261 두 점 사이의 거리
 * Category : 분할정복
 * Date: 2021/04/14
 * ref - https://steady-coding.tistory.com/215
 */

public class BJ_2261 {

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ArrayList<Point> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list.add(new Point(x, y));
        }
        Collections.sort(list, (p1, p2) -> Integer.compare(p1.x, p2.x)); // x좌표 기준으로 오름차순 정렬.

        System.out.println(solution(list, 0, N - 1));

    }

    // 두 점 사이 거리의 제곱
    public static int dist(Point p1, Point p2) {
        int x = p1.x - p2.x;
        int y = p1.y - p2.y;

        return (x * x) + (y * y);
    }

    // 완전 탐색으로 가장 가까운 거리 찾기
    static int bruteForce(ArrayList<Point> list, int start, int end) {
        int min = Integer.MAX_VALUE;

        for (int i = start; i < end; i++) {
            for (int j = i + 1; j <= end; j++) {

                int diff = dist(list.get(i), list.get(j));
                min = Math.min(diff, min);
            }
        }

        return min;
    }

    public static int solution(ArrayList<Point> arrList, int start, int end) {
        int n = end - start + 1; //좌표 개수

        // 좌표 개수가 3 이하면 완전탐색으로 가장 가까운 두 점 사이의 거리를 찾기
        if (n <= 3) {
            return bruteForce(arrList, start, end);
        }

        int mid = (start + end) / 2;

        // 중앙선을 기준으로 왼쪽 점들 중 가장 작은 거리 = d1 (1. 가장 가까운 두 점이 왼쪽 영역에 있는 경우)
        // 오른쪽 점들 중 가장 작은 거리 = d2 (2. 가장 가까운 두 점이 오른쪽 영역에 있는 경우)
        // min(d1, d2)를 구하면 된다.
        int d = Math.min(solution(arrList, start, mid), solution(arrList, mid + 1, end));

        //3. 가장 가까운 두 점이 왼쪽과 오른쪽 영역 하나씩 있는 경우 : 중앙선을 기점으로 d만큼 떨어진 점만 본다.
        // - 중앙선을 기준으로 양쪽으로 d 거리 이내에 들어오는 점들만 보면 됨.
        ArrayList<Point> band = new ArrayList<>();

        for (int i = start; i <= end; i++) {
            int tx = arrList.get(mid).x - arrList.get(i).x;

            if (tx * tx < d) { // d 미만이면 추가
                band.add(arrList.get(i));
            }
        }

        Collections.sort(band, (p1, p2) -> Integer.compare(p1.y, p2.y)); //y좌표 기준 오름차순 정렬

        // y좌표를 기준으로 오름차순 정렬된 band의 각 요소(P1, P2, ...)는
        // 자기보다 큰 요소만 보면서 거리를 측정함.
        for (int i = 0; i < band.size() - 1; i++) {
            for (int j = i + 1; j < band.size(); j++) {
                int ty = band.get(j).y - band.get(i).y;

                if (ty * ty < d) { //d 미만
                    d = Math.min(d, dist(band.get(i), band.get(j)));
                } else { //y좌표 기준으로 오름차순 정렬되어있으므로, d보다 거리가 큰 순간이 오면 반복문 종료
                    break;
                }
            }
        }

        return d;
    }

}