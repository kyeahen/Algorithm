package Programmers.Resolving;

import java.util.LinkedList;
import java.util.Queue;

class Node {
    String word;
    int edge;

    Node(String word, int edge) {
        this.word = word;
        this.edge = edge;
    }
}

//단어변환 - 20.10.09
public class P_43163 {

    static boolean[] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        String b = "hit";
        String t = "cog";
        String[] str = {"hot", "dot", "dog", "lot", "log", "cog"};

        System.out.println(solution(b, t, str));
    }

    public static int solution (String begin, String target, String[] words) {
        visited = new boolean[words.length];

        dfs(words, begin, target, 0);
        if (min == Integer.MAX_VALUE) {
            return 0;
        } else {
            return min;
        }

        //return bfs(words, begin, target);
    }

    public static int bfs(String[] words, String begin, String target) {
        boolean[] visited = new boolean[words.length];
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(begin, 0));

        while (!q.isEmpty()) {
            Node n = q.poll();
            String current = n.word;

            if (current.equals(target)) {
                return n.edge;
            }

            for (int i = 0; i < words.length; i++) {
                if (check(current, words[i]) && !visited[i]) {
                    System.out.println(current + " " + words[i] + " " + n.edge);
                    q.add(new Node(words[i], n.edge + 1));
                    visited[i] = true;
                }
            }
        }

        return 0;
    }

    public static void dfs(String[] words, String begin, String target, int count) {
        if (begin.equals(target)) {
            min = Math.min(count, min);
            return;
        }

        for (int i = 0; i < words.length; i++) {
            if (check(begin, words[i]) && !visited[i]) {
                System.out.println(begin + " " + words[i] + " " + count);
                visited[i] = true;
                dfs(words, words[i], target, count + 1);
                visited[i] = false;
            }
        }

        return;
    }

    public static boolean check(String current, String str) {

        int count = 0;
        for (int i = 0; i < current.length(); i++) {
            if (current.charAt(i) != str.charAt(i)) {
                count += 1;
            }
        }

        if (count == 1) {
            return true;
        }

        return false;
    }
}
