package Programmers;

//N개의 최소공배수 - level2
public class P_12953 {

    public static void main(String[] args) {
        int[] arr = {2, 6, 8, 14};

        System.out.print(solution(arr));
    }

    public static int solution(int[] arr) {

        int min = arr[0] * arr[1] / gcd(arr[0], arr[1]); //최소공배수
        for (int i = 2; i < arr.length; i++) {
            min = min * arr[i] / gcd(min, arr[i]);
        }

        return min;
    }

    //최대공약수
    public static int gcd(int a, int b) {

        int max = 0;
        for (int i = 1; i <= b; i++) {

            if (a % i == 0 && b % i == 0) {
                max = i;
            }
        }

        return max;
    }
}
