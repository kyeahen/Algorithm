package Baekjoon;
import java.util.*;

// ! 다시 풀어보기

//최소비용 구하기2 - 알고리즘 스터디(공통)
// - 그래프 (다익스트라 알고리즘)
// https://gre-eny.tistory.com/m/20

class Node_graph implements Comparable<Node_graph> {
    int city;
    int cost;

    Node_graph (int city, int cost) {
        this.city = city;
        this.cost = cost;
    }

    @Override
    public int compareTo(Node_graph o) {
        return Integer.compare(cost, o.cost);
    }
}

public class BJ_11779 {

    static int n, m;
    static ArrayList<Node_graph>[] graph;

    static int[] dist, precity;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        m = sc.nextInt();

        graph = new ArrayList[n + 1];
        precity = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int cost = sc.nextInt();

            graph[start].add(new Node_graph(end, cost));
        }

        int s = sc.nextInt();
        int e = sc.nextInt();

        dijkstra(s);

        String ans = dist[e] + "\n";

        Stack<Integer> stack = new Stack<>();
        stack.push(e);

        int count = 0;
        while (precity[e] != 0) {
            count++;
            stack.push(precity[e]);
            e = precity[e];
        }

        ans += (count + 1) + "\n";

        while (!stack.isEmpty()) {
            ans += stack.pop() + " ";
        }

        System.out.println(ans);
    }

    public static void dijkstra(int start) {
        Queue<Node_graph> q = new LinkedList<>();
        dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[start] = 0;
        q.offer(new Node_graph(start, 0));

        while (!q.isEmpty()) {
            Node_graph v = q.poll();
            int current = v.city;

            for (Node_graph next : graph[current]) {

                if (dist[next.city] > dist[current] + next.cost) {
                    dist[next.city] = dist[current] + next.cost;

                    precity[next.city] = current;
                    q.offer(new Node_graph(next.city, dist[next.city]));
                }
            }
        }
    }
}
