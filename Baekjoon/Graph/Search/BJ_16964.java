package Baekjoon.Graph.Search;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

//DFS 스페셜 저지 - 알고리즘 스터디 (공통)
// dfs
public class BJ_16964 {

    static int N;
    static ArrayList<ArrayList<Integer>> node = new ArrayList<>();
    static boolean[] visited;
    static int[] answer;

    static int ansIdx = 1;
    static int flag = 0;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();

        visited = new boolean[N + 1];
        answer = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            node.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < N - 1; i++) {
            int a = s.nextInt();
            int b = s.nextInt();

            node.get(a).add(b);
            node.get(b).add(a);
        }

        //print();

        for (int i = 0; i < N; i++) {
            answer[i] = s.nextInt();
        }

        dfs(1);
        System.out.print(flag);
    }

    public static void dfs(int x) {

        if (ansIdx == N) {
            flag = 1;
            return;
        }

        if (visited[x]){
            //System.out.println(x + "는 방문");
            return;
        }

        visited[x] = true;
        //System.out.println("\n" + "dfs" + " " + x);

        for (int i = 0; i < node.get(x).size(); i++) {

            int v = node.get(x).get(i); //x와 연결된 정점
            //System.out.println("--for " + i + " " + v);

            if (!visited[v]) { //해당 정점을 방문하지 않았으면

                if (answer[ansIdx] == v) { //해당 정점이 주어진 순서와 일치하면
                    //System.out.println(i +  " " + v);
                    ansIdx++;
                    dfs(v);

                    //백트래킹
                    // - 재귀에서 리턴되지 않고 나왔으면 연결된 정점을 더이상 찾을 수 없는 것,
                    // 이전 정점으로 돌아가 탐색하지 않은 곳이 있는지 확인
                   i = -1;
                   //System.out.println(i +  "d" );
                }
            }
            //System.out.println("통과\n");
        }

    }

    //인접리스트 출력
    public static void print() {

        for(int i = 0; i <= N;i++) {
            Iterator<Integer> iter = node.get(i).iterator();
            System.out.print(i);
            if(iter.hasNext()) System.out.print("-");
            while(iter.hasNext()) System.out.print(iter.next() + " ");
            System.out.println();
        }
    }
}
