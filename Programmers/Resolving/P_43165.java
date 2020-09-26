package Programmers.Resolving;
import java.util.LinkedList;
import java.util.Queue;

//타겟 넘버 - 20.09.26
public class P_43165 {

    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.print(solution(numbers, target));
    }

    public static int solution(int[] numbers, int target) {
        int count = 0;
        //count = dfs(numbers, target, 0, 0);
        count = bfs(numbers, target);
        return count;
    }

    public static int dfs(int[] numbers, int target, int depth, int sum) {
        if (depth == numbers.length) {
            if (sum == target) {
                return 1;
            }
            return 0;
        }

        int answer = 0;
        answer += dfs(numbers, target, depth + 1, sum + numbers[depth]);
        answer += dfs(numbers, target, depth + 1, sum - numbers[depth]);
        return answer;
    }

    public static int bfs(int[] numbers, int target) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(numbers[0], 0));
        q.add(new Pair(-numbers[0], 0));

        int count = 0;
        while (!q.isEmpty()) {
            Pair p = q.poll();

            if (p.depth == numbers.length - 1) {
                if (p.sum == target) {
                    count += 1;
                }
                continue;
            }

            q.add(new Pair(p.sum + numbers[p.depth], p.depth + 1));
            q.add(new Pair(p.sum - numbers[p.depth], p.depth + 1));
        }

        return count;
    }
}

class Pair {
    int sum;
    int depth;

    Pair (int sum, int depth) {
        this.sum = sum;
        this.depth = depth;
    }
}
