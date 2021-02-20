package Baekjoon.Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by kyeahen.
 * Title : 2212 센서
 * Date: 2021/02/01
 */

public class BJ_2212 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt(); //센서 개수
        int k = s.nextInt(); //집중국 개수
        int[] points = new int[n]; //센서 좌표 저장

        for (int i = 0; i < n; i++) {
            points[i] = s.nextInt();
        }

        Arrays.sort(points); //오름차순 정렬 (센서 순서대로 배치)

        ArrayList<Integer> dist = new ArrayList<>(); //센서 좌표 간 거리 저장
        int temp = points[0];

        for (int i = 1; i < n; i++) {
            int diff = points[i] - temp;
            dist.add(diff);
            temp = points[i];
        }

        Collections.sort(dist); //오름차순 정렬

        int ans = 0;
        int diff = k - 1; //집중국 개수 - 1만큼 센서-센서 간의 거리를 한번 건너뛴다. (거리가 긴 구간들)
        for (int i = 1; i <= dist.size() - diff; i++) { // 거리 배열에서 diff 값을 빼면 계산해야하는 거리값들만 남음
            ans += dist.get(i - 1);
        }

        System.out.println(ans);
    }
}
