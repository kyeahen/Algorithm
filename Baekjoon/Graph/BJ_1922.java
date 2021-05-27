package Baekjoon.Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by kyeahen.
 * Title : 1922 네트워크 연결
 * Category : 그래프, 크루스칼 알고리즘
 * Date: 2021/05/26
 * ref - https://brenden.tistory.com/36 (크루스칼 알고리즘)
       - https://blog.naver.com/ndb796/221230994142 (크루스칼 알고리즘)
 */
public class BJ_1922 {

    static class Edge implements Comparable<Edge>{
        int v1;
        int v2;
        int cost;

        Edge(int v1, int v2, int cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) { //오름차순 (비용)
            return cost - o.cost;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "v1=" + v1 +
                    ", v2=" + v2 +
                    ", cost=" + cost +
                    '}';
        }
    }

    static int N, M;

    static ArrayList<Edge> edgeList;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine()); //컴퓨터 수
        M = Integer.parseInt(br.readLine()); //연결할 수 있는 선의 수

        edgeList = new ArrayList<>();
        parent = new int[N + 1];

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()); //비용

            edgeList.add(new Edge(a, b, c));
        }

        //각 노드가 자기 자신을 루트 노드로 갖도록 초기화
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        Collections.sort(edgeList);

        int sum = 0;
        for (int i = 0; i < edgeList.size(); i++) {
            Edge e = edgeList.get(i);

            //동일한 루트 노드를 같지 않는 경우, 사이클이 발생하지 않을 때만 선택
            if (!isSameParent(e.v1, e.v2)) {
                sum += e.cost;
                union(e.v1, e.v2);
            }
        }

        System.out.println(sum);
    }

    //union
    // - 두개의 집합을 하나의 집합으로 합친다.
    public static void union(int x, int y) {
        x = find(x); //x의 루트 노드 찾기
        y = find(y); //y의 루트 노드 찾기

        if (x != y) { //x와 y가 같은 노드가 아니면
            parent[y] = x; //y의 루트 노드를 x로 갱신
        }
    }

    //find
    // - 원소가 주어졌을 때, 원소의 루트노드 반환
    public static int find(int x) {
        if (parent[x] == x) { //x의 루트 노드가 자신이면 자기 자신 반환
            return x;
        }

        return parent[x] = find(parent[x]); //x의 루트 노드의 루트노드 찾아서 반환
    }

    //루트 노드가 같은지 체크
    public static boolean isSameParent(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) { //같은 루트 노드이면
            return true;
        }

        return false;
    }



}