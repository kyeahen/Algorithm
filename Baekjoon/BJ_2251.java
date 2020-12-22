package Baekjoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//물통 - bfs
public class BJ_2251 {

    static int[] from = {0, 0, 1, 1, 2, 2};
    static int[] to = {1, 2, 0, 2, 0, 1};

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int[] water = new int[3];
        for (int i = 0; i < 3; i++) {
            water[i] = s.nextInt();
        }
    }

    public static void bfs() {
        Queue<Point> q = new LinkedList<>();
    }
}
