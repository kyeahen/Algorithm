package Programmers;

//소수 만들기 - level2
public class P_12977 {

    static int count = 0;

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};

        System.out.print(solution(nums));
    }

    public static int solution(int[] nums) {
        boolean[] visited = new boolean[nums.length];

        combination(nums, visited, 0, nums.length, 3);
        return count;
    }

    // 조합 : 중복X, 순서X - 백트래킹
    public static void combination(int[] nums, boolean[] visited, int start, int n, int r) {
        if (r == 0) {

            int sum = 0;
            for (int i = 0; i < n; i++) { // 3개의 수 합 구하기
                if (visited[i]) {
                    sum += nums[i];
                }
            }

            if (isPrime(sum)) { //합이 소수인지 확인하기
                count++; //소수이면 값 증가
            }

            return;

        } else {
            for (int i = start; i < n; i++) {
                visited[i] = true;
                combination(nums, visited, i + 1, n, r - 1);
                visited[i] = false;
            }
        }
    }

    // 소수 판별 함수
    public static boolean isPrime(int n){

        if (n <= 1) {
            return false;
        }

        int a = (int) Math.sqrt(n);
        for (int j = 2; j <= a; j++) {
            if (n % j == 0) {
                return false;
            }
        }

        return true;
    }
}
