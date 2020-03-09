package Programmers;
import java.util.ArrayList;

// 소수 찾기 - level2
public class P_42839 {

    static ArrayList<Integer> resultArr = new ArrayList<>();

    public static void main(String[] args) {
        String numbers = "011";
        System.out.print(solution(numbers));
    }

    public static int solution(String numbers) {
        char[] temp = numbers.toCharArray();

        int[] arr = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            arr[i] = temp[i] - '0';
        }

        for (int i = 1; i <= temp.length; i++) {
            permutation(arr, 0, temp.length, i);
        }

        return resultArr.size();
    }

    // 순열 : 순서O, 중복X
    static void permutation(int[] arr, int depth, int n, int r) {
        if(depth == r) {

            String s = "";
            for (int i = 0; i < r; i++) {
                s += arr[i];
            }

            // 소수일 때, 중복된 수가 아닐 때, 저장하기
            if (isPrime(Integer.valueOf(s)) && !resultArr.contains(Integer.valueOf(s)) ) {
                resultArr.add(Integer.valueOf(s));
            }

            return;
        }

        for (int i = depth; i < n; i++) {
            swap(arr, depth, i);
            permutation(arr, depth + 1, n, r);
            swap(arr, depth, i);
        }
    }

    static void swap(int[] arr, int depth, int i) {
        int temp = arr[depth];
        arr[depth] = arr[i];
        arr[i] = temp;
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
