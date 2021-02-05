package Baekjoon.Greedy;

import java.util.HashSet;
import java.util.Scanner;

//공항 - 알고리즘 스터디(공통)
// - 그리디 (Union-Find)
// https://mygumi.tistory.com/245
// https://steady-coding.tistory.com/114
public class BJ_10775 {

    static int[] parent;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int G = s.nextInt(); //게이트의 수
        int P = s.nextInt(); //비행기의 수

        parent = new int[G + 1];

        //각 노드가 자기 자신을 루트 노드로 갖도록 초기화
        for (int i = 1; i <= G; i++) {
            parent[i] = i;
        }

        int ans = 0;
        for (int i = 1; i <= P; i++) {

            //되도록이면 g에 도킹하고(최선), g에 도킹할 수 없는 경우에는 g-1에 도킹(차선책)
            // g-1, g-2..0번까지 탐색
            int g = s.nextInt();
            int docking = find(g);

            //최종적으로 find 호출 시, 0이 나오면 도킹할 게이트가 없다는 의미
            if (docking == 0) {
                break;
            } else {

                //도킹이 가능하다면 (도킹한 게이트 - 1)과 union 해준다. (차선책 선택이 가능하도록)
                merge(docking, docking - 1);
                ans++;
            }
        }

        System.out.println(ans);
    }

    //union
    // - 두개의 집합을 하나의 집합으로 합친다.
    public static void merge(int u, int v) {
        u = find(u); //u의 루트 노드 찾기
        v = find(v); //v의 루트 노트 찾기

        parent[u] = v; //u의 루트 노드를 v의 루트 노드로 갱신
    }

    //find
    // - 원소가 주어졌을 때, 원소의 루트노드 반환
    public static int find(int u) {
        if (u == parent[u]) { //자신이 루트 노드이면 자신 반환
            return u;
        }

        return parent[u] = find(parent[u]); //압축 (빠른 속도)
    }

    //5%
    public static void none(String[] args) {
        Scanner s = new Scanner(System.in);

        int G = s.nextInt(); //게이트의 수
        int P = s.nextInt(); //비행기의 수

        HashSet<Integer> set = new HashSet<>(); //도킹 게이트 저장 (중복 체크)

        int plain = 0;
        for (int i = 0; i < P; i++) { //비행기 순서대로 도착 (영구 도킹)
            int g = s.nextInt(); //주어진 게이트

            if (plain > P) { //비행기 수를 넘으면 그만
                break;
            }

            if (!set.contains(g)) { //아직 도킹되지 않은 게이트면
                plain++; //다음 비행기
                set.add(g); //게이트 저장
            }
        }

        System.out.println(plain);
    }
}
