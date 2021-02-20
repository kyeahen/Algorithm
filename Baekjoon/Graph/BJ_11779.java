package Baekjoon.Graph;
import java.util.*;

// ! 다시 풀어보기

//최소비용 구하기2 - 알고리즘 스터디(공통)
// - 그래프 (다익스트라 알고리즘) / 최단거리 + 역추적
// https://gre-eny.tistory.com/m/20
// https://dragon-h.tistory.com/39

public class BJ_11779 {

    static class Node_graph implements Comparable<Node_graph> {
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

    static int n, m;
    static ArrayList<Node_graph>[] graph;
    static int[] dist, precity;

    static final int INF = 987654321;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt(); //도시의 개수(정점)
        m = sc.nextInt(); //버스의 개수(간선)

        graph = new ArrayList[n + 1];
        dist = new int[n + 1]; //최단 경로 저장 배열
        precity = new int[n + 1]; //이전 도시(부모 노드) 저장 배열 (역추적 사용)

        Arrays.fill(dist, INF); //배열 초기화

        //인접리스트 초기화
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int start = sc.nextInt(); //시작 도시
            int end = sc.nextInt(); //도착 도시
            int cost = sc.nextInt(); //비용 (가중치)

            graph[start].add(new Node_graph(end, cost));
        }

        int s = sc.nextInt(); //구하고자하는 시작 도시
        int e = sc.nextInt(); //구하고자하는 도착 도시

        dijkstra(s); //다익스트라

        //역추적
        Stack<Integer> stack = new Stack<>();
        stack.push(e);

        // 1. 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력
        String ans = dist[e] + "\n";

        int count = 0; //도시 개수 카운팅
        while (precity[e] != 0) {
            count++;
            stack.push(precity[e]);
            e = precity[e]; //이전 도시로 갱신
        }

        // 2. 최소 비용을 갖는 경로에 포함되어있는 도시의 개수를 출력
        ans += (count + 1) + "\n"; // "출발 도시와 도착 도시도 포함한다."

        // 3. 최소 비용을 갖는 경로를 방문하는 도시 순서대로 출력
        while (!stack.isEmpty()) {
            ans += stack.pop() + " ";
        }

        System.out.println(ans);
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node_graph> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[n + 1];

        //시작 정점은 거리 0
        pq.add(new Node_graph(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node_graph v = pq.poll();
            int current = v.index; //현재 정점

            if (visited[current]) { //이미 방문한 도시면 건너뛰기
                continue;
            }

            visited[current] = true;

            for (Node_graph next : graph[current]) {

                //특정 도시 거리 = 현재 도시 거리 + 특정 도시 거리
                if (dist[next.index] > dist[current] + next.distance) { //최단 경로면 저장
                    dist[next.index] = dist[current] + next.distance;
                    pq.add(new Node_graph(next.index, dist[next.index]));
                    precity[next.index] = current;
                }
            }
        }
    }
}
