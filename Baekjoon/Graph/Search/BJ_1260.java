package Baekjoon.Graph.Search;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// DFS와 BFS
public class BJ_1260 {

    static int node[][]; // 인접행렬 배열
    static boolean check[]; // 노드의 방문여부 표시 배열
    static Queue<Integer> q = new LinkedList<>(); // BFS를 위한 큐

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int N = s.nextInt(); // 정점의 개수
        int M = s.nextInt(); // 간선의 개수
        int V = s.nextInt(); // 탐색을 시작할 정점의 번호

        node = new int[N + 1][N + 1];
        check = new boolean[N + 1];

        for (int i = 0; i < M; i++) { // 인접행렬로 그래프를 구현

            int a = s.nextInt();
            int b = s.nextInt();
            node[a][b] = 1;
            node[b][a] = 1;
        }

        dfs(V);
        Arrays.fill(check, false); // DFS 이후 동일한 방문 여부 배열을 사용하기 때문에 다시 false으로 초기화 해준다.
        System.out.println();
        bfs(V);
    }

    // 재귀, 인접행렬
    public static void dfs(int v) {
        int n = node.length - 1;

        check[v] = true; // 방문하지 않은 노드라면 방문여부를 표시
        System.out.print(v + " "); // 출력

        for (int i = 1; i <= n; i++) {

            if (node[v][i] == 1 && !check[i]) { // 방문하지 않은 노드일 경우
                dfs(i); // 해당 노드로 이동
            }
        }

    }

    // 큐, 인접행렬
    public static void bfs(int v) {
        int n = node.length - 1;

        q.offer(v); // 큐에 시작노드 삽입
        check[v] = true; // 시작 노드에 방문 표시

        while (!q.isEmpty()) { // 공백 큐가 될 때까지 반복
            v = q.poll(); // 큐에서 하나 꺼내기
            System.out.print(v + " "); // 출력

            for (int i = 1; i <= n; i++) { // 큐에서 꺼낸 노드와 연결된 노드를 탐색

                if (node[v][i] == 1 && !check[i]) { // 큐에서 꺼낸 노드와 연결된 노드가 방문하지 않았던 노드이면
                    q.offer(i); // 큐에 삽입
                    check[i] = true; // 방문 표시
                }
            }

        }
    }
}
