package Programmers;

//네트워크(DFS) - Level3
public class P_43162 {

    static boolean[][] visited;

    public static void main(String[] args) {
        int n = 3;
        int[][] com = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(solution(n, com));
    }

    public static int solution(int n, int[][] computers) {
        visited = new boolean[n][n];

        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i][i]) {
                count++;
                dfs(computers, i, n);
            }
        }

        return count;
    }

    public static void dfs(int[][] computers, int index, int n) {

        for (int i = 0; i < n; i++) {
            if (computers[index][i] == 1 && !visited[index][i]) {
                visited[index][i] = visited[i][index] = true;
                dfs(computers, i, n);
            }
        }
    }
}
