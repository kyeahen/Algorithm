package Baekjoon.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

// [11779 최소비용 구하기2]
//최단경로 - 알고리즘 스터디(개인)
// - 그래프 (다익스트라 알고리즘)
// https://dragon-h.tistory.com/20

/*
다익스트라 알고리즘 : 모든 정점까지의 최단 경로 구하기
 */

class Node_graph implements Comparable<Node_graph> {
    int index; //정점
    int distance; //가중치

    Node_graph (int index, int distance) {
        this.index = index;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node_graph o) {
        return Integer.compare(distance, o.distance);
    }
}

public class BJ_1753 {

    static int V, E, K;
    static ArrayList<Node_graph>[] graph;
    static int[] dist;

    static final int INF = 987654321;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        V = sc.nextInt(); //정점 개수
        E = sc.nextInt(); //간선 개수
        K = sc.nextInt(); //시작 정점 번호

        graph = new ArrayList[V + 1];
        dist = new int[V + 1]; //최단 경로 저장 배열(distance)

        Arrays.fill(dist, INF); //배열 초기화 (가장 큰 값)

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
        }

        //리스트에 그래프 정보 초기화
        for (int i = 0; i < E; i++) {
            int u = sc.nextInt(); //시작 정점
            int v = sc.nextInt(); //도착 정점
            int w = sc.nextInt(); //가중치

            graph[u].add(new Node_graph(v, w));
        }

        dijkstra(K); //다익스트라 알고리즘

        String ans = "";
        for (int i = 1; i <= V; i++) {
            if (dist[i] == INF) { //경로가 존재하지 않는 경우
                ans += "INF\n";
            } else {
                ans += dist[i] + "\n";
            }
        }

        System.out.println(ans);
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node_graph> pq = new PriorityQueue<>(); //우선순위큐 (최소값부터 poll)
        boolean[] visited = new boolean[V + 1]; //방문 체크 배열

        //시작 정점은 거리 0
        pq.add(new Node_graph(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node_graph node = pq.poll();
            int current = node.index; //현재 정점

            if (visited[current]) { //방문한 정점이면 건너뛰기
                continue;
            }

            visited[current] = true; //현재 정점 방문 체크

            for (Node_graph n : graph[current]) {

                //특정 정점 = 현재 정점 거리 + 특정 정점 가중치
                if (dist[n.index] > dist[current] + n.distance) { //최단경로이면 저장 및 갱신하기
                    dist[n.index] = dist[current] + n.distance;
                    pq.add(new Node_graph(n.index, dist[n.index]));
                }
            }
        }
    }
}
