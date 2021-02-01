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

        Arrays.sort(points); //오름차순 정렬

        ArrayList<Integer> dist = new ArrayList<>(); //센서 좌표 간 거리 저장

        int temp = points[0];
        for (int i = 1; i < n; i++) {
            int diff = points[i] - temp;
            dist.add(diff);
            temp = points[i];
        }

        Collections.sort(dist); //오름차순 정렬

        int ans = 0;
        int diff = k - 1;
        for (int i = 1; i <= dist.size() - diff; i++) {
            ans += dist.get(i - 1);
        }

        System.out.println(ans);
    }
}
