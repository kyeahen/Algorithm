package Baekjoon.Graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by kyeahen.
 * Title : 1197 최소 스패닝 트리
 * Category : 그래프, 크루스칼 알고리즘
 * Date: 2021/05/27
 */
public class BJ_1197 {

    static class Edge implements Comparable<Edge> {
        int v1;
        int v2;
        int cost;

        Edge(int v1, int v2, int cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }

    static int[] parent;
    static ArrayList<Edge> list;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int V = s.nextInt();
        int E = s.nextInt();

        parent = new int[V + 1];
        list = new ArrayList<>();

        for (int i = 0; i < E; i++) {
            int a = s.nextInt();
            int b = s.nextInt();
            int c = s.nextInt(); //가중치

            list.add(new Edge(a, b, c));
        }

        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        Collections.sort(list);
        System.out.println(solution(V, E));
    }

    public static int solution(int V, int E) {
        int sum = 0;

        for (int i = 0; i < list.size(); i++) {
            Edge e = list.get(i);

            if (!isSameParent(e.v1, e.v2)) {
                sum += e.cost;
                union(e.v1, e.v2);
            }
        }

        return sum;
    }

    public static void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
        }
    }

    public static int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    public static boolean isSameParent(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) {
            return true;
        }

        return false;
    }
}
