package Programmers;

//타겟 넘버 - level2
public class P_43165 {

    static int result = 0;

    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;

        System.out.println(solution(numbers, target));
    }

    public static int solution(int[] numbers, int target) {
        dfs(numbers, target, 0);
        return result;
    }

    // dfs - 재귀
    public static void dfs (int[] numbers, int target, int depth) {

        if (depth == numbers.length) {

            int sum = 0;
            for (int i = 0; i < numbers.length; i++) {
                sum += numbers[i];
            }

            if (sum == target) {
                result++;
            }

            return;

        } else {
            // + 연산은 왼쪽 자식 노드로
            numbers[depth] *= 1;
            dfs(numbers, target, depth + 1);

            // - 연산은 오른쪽 자식 노드로
            numbers[depth] *= -1;
            dfs(numbers, target, depth + 1);
        }
    }
}
