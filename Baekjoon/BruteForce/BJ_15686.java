package Baekjoon.BruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by kyeahen.
 * Title : 15685 치킨배달
 * Category : 구현, 완전탐색
 * Date: 2021/03/16
 * ref -
 */
public class BJ_15686 {

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N;
    static int M;
    static int[][] map;
    static ArrayList<Point> chickens;
    static ArrayList<Point> houses;
    static Stack<Point> selectChicken;
    static int minDist = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];

        chickens = new ArrayList<>();
        houses = new ArrayList<>();
        selectChicken = new Stack<>();

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 1) {
                    //가정집
                    houses.add(new Point(i, j));
                } else if (map[i][j] == 2) {
                    //치킨집
                    chickens.add(new Point(i, j));
                }
            }
        }

        select(0, 0);

        System.out.println(minDist);
    }

    static void select(int start, int count) {
        if (count == M) {
            calcDist();
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            selectChicken.push(chickens.get(i));
            select(i + 1, count + 1);
            selectChicken.pop();
        }
    }

    static void calcDist() {
        int sum = 0;
        for (Point house : houses) {
            int min = Integer.MAX_VALUE;
            for (Point chicken : selectChicken) {
                int dist = Math.abs(house.x - chicken.x) + Math.abs(house.y - chicken.y);
                min = Math.min(min, dist);
            }
            sum += min;

            if (sum > minDist) {
                return;
            }
        }
        minDist = Math.min(sum, minDist);

    }

}
