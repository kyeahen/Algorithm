package Programmers;

import java.util.LinkedList;
import java.util.Queue;

class Node {
    String word; //현재 단어
    int edge; //간선

    Node(String word, int edge) {
        this.word = word;
        this.edge = edge;
    }
}

//단어변환 lv.3 - dfs, bfs
public class P_43163 {

    static boolean[] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        String b = "hit";
        String t = "cog";
        String[] w = {"hot", "dot", "dog", "lot", "log", "cog"};

        System.out.println(solution(b, t, w));
    }

    public static int solution(String begin, String target, String[] words) {

        visited = new boolean[words.length];

        //dfs
        dfs(words, begin, target, 0);

        if (min == Integer.MAX_VALUE) {
            return 0;
        }

        return min;

        //bfs
        //return bfs(words, begin, target);
    }

    public static void dfs(String[] words, String begin, String target, int count) {
        if (begin.equals(target)) {
            min = Math.min(count, min);
            return;
        }

        for (int i = 0; i < words.length; i++) {

            if (!visited[i] && check(begin, words[i])) {
                visited[i] = true;
                dfs(words, words[i], target, count + 1);
                visited[i] = false;
            }
        }
    }

    public static int bfs(String[] words, String begin, String target) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(begin, 0));

        while (!q.isEmpty()) {
            Node n = q.poll();
            String current = n.word;

            if (current.equals(target)) {
                return n.edge;
            }

            for (int i = 0; i < words.length; i++) {

                if (!visited[i] && check(current, words[i])) {
                    q.add(new Node(words[i], n.edge + 1));
                    visited[i] = true;
                }
            }
        }

        return 0;
    }

    //1개의 알파벳만 다른지 확인하는 메소드
    public static boolean check(String prev, String current) {

        int count = 0;
        for (int i = 0; i < prev.length(); i++) {
            if (prev.charAt(i) != current.charAt(i)) {
                count++;
            }
        }

        if (count == 1) {
            return true;
        }

        return false;
    }
}

