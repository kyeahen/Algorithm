package Baekjoon.Graph.Search;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//스타트링크 - bfs
public class BJ_5014 {

    static int F, S, G, U, D;
    static int[] map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // "건물은 1층부터 시작하고, 가장 높은 층은 F층이다."
        F = sc.nextInt();
        S = sc.nextInt(); //강호
        G = sc.nextInt(); //스타트링크

        U = sc.nextInt(); //위로 U층을 가는 버튼
        D = sc.nextInt(); //아래로 D층을 가는 버튼

        map = new int[F + 1];

        bfs();

        if (map[G] == 0) { //G층을 방문하지 못했으면
            System.out.println("use the stairs");
        } else {
            System.out.println(map[G] - 1); //방문 체크하면서 1부터 시작했기 때문에 -1을 해준다.
        }
    }

    public static void bfs() {
        Queue<Integer> q = new LinkedList<>();
        q.add(S); //큐에 현재 층 삽입
        map[S] = 1; //S층 방문 체크

        while (!q.isEmpty()) {
            int s = q.poll();

            if (s == G) { //현재 층과 스타트링크 층(G)이 동일하면 탐색 종료
                return;
            }

            if (s + U <= F) { //현재 층에서 U층 올라간 층이 F층 이하일 때 (유효 범위 체크)

                if (map[s + U] == 0) { //아직 해당 층을 방문하지 않았으면
                    q.add(s + U);
                    map[s + U] = map[s] + 1; //버튼 수 카운팅(+1)
                }
            }

            if (s - D > 0) { //현재 층에서 D층 내려간 층이 존재하는 층일 때 (유효 범위 체크)

                if (map[s - D] == 0) { //아직 해당 층을 방문하지 않았으면
                    q.add(s - D);
                    map[s - D] = map[s] + 1; //버튼 수 카운팅(+1)
                }
            }
        }
    }
}
