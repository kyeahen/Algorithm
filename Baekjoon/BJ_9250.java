package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by kyeahen.
 * Title : 9250 문자열집합판별
 * Category : 아호-코라식
 * Date: 2021/02/17

 * ref - https://hellogaon.tistory.com/17
       - https://blog.naver.com/kerochuu/222035299045
 */

public class BJ_9250 {

    private static class Node {
        boolean isEnd, isRoot;
        Node[] child; //자식 노드
        Node fail; //이 노드에서 매칭이 실패했을 때, 다음으로 가서 시도해야할 노드의 포인터

        Node(boolean isRoot) {
            child = new Node[size];
            this.isRoot = isRoot;
        }

        Node setChild(char data) {
            if (child[data - 'a'] == null) { //해당 자식 노드가 없으면
                return child[data - 'a'] = new Node(false); //자식 노드 생성
            }

            return child[data - 'a'];
        }
    }

    private static class Tries {
        Node root = new Node(true); // true = root

        void add(String s) {
            Node n = root;

            for (int i = 0; i< s.length(); i++) {
                n = n.setChild(s.charAt(i));
            }

            n.isEnd = true;
        }
    }

    static int N;
    static int size = 26;

    static Tries tries = new Tries();
    static Queue<Node> q = new LinkedList<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            tries.add(br.readLine());
        }

        //bfs 구현 (kmp 전처리)
        init_bfs();
        bfs();

        //kmp
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {

            if (kmp(br.readLine(), tries.root)) { //집합에 속하면
                sb.append("YES" + "\n");
            } else {
                sb.append("NO" + "\n");
            }
        }

        System.out.print(sb.toString());
    }

    public static void init_bfs() {
        tries.root.fail = tries.root; //루트의 fail 함수는 자기 자신
        q.add(tries.root); //루트 노드 추가
    }

    // Failure Link (실패 연결)
    public static void bfs() {

        // * trie의 루트 노드로부터 bfs를 수해하면서 실패 연결을 만든다. *

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int i = 0; i < size; i++) {
                Node child = node.child[i]; //현재 노드의 자식 노드 (알파벳)

                if (child != null) { //해당 알파벳이 없으면

                    if (node.isRoot) { // 루트 노드이면
                        child.fail = tries.root; //child의 실패 연결은 루트

                        // - 1레벨 노드의 실패 연결은 항상 루트
                        // - root의 다음 depth까지 fail은 root를 가리킴

                    } else { //루트 노드가 아니면
                        Node failure = node.fail; //node의 실패 연결

                        // * 실패 링크에서 child와 같은 문자로 이어지는 자식 노드가 있는지 찾기 *

                        while (!failure.isRoot && failure.child[i] == null) { //루트 노드가 아니고, 자식 노드가 null일 동안
                            failure = failure.fail; //찾지 못했으면, "실패 연결" 대입
                        }

                        if (failure.child[i] != null) { //자식 노드가 null이 아니면
                            failure = failure.child[i]; //찾으면, 해당 "자식 노드" 대입
                        }

                        child.fail = failure;
                    }

                    if (child.fail.isEnd) { //모두 탐색했으면
                        child.isEnd = true;
                    }

                    q.add(child);
                }
            }
        }
    }

    public static boolean kmp(String input, Node n) {
        Node node = n; //그냥 n 가져다쓰면 tries.root 값 변함 (call by ref)

        for (int i = 0; i < input.length(); i++) {
            int current = input.charAt(i) - 'a';

            // * current와 같은 문자로 이어지는 자식 노드가 있는지 찾기 *

            //찾지 못했으면, "실패 연결" 대입
            while (!node.isRoot && node.child[current] == null) {
                node = node.fail;
            }

            //찾으면, 해당 "자식 노드" 대입
            if (node.child[current] != null) {
                node = node.child[current];
            }

            if (node.isEnd) { //모두 탐색했으면
                return true;
            }

        }

        return false;
    }
}
