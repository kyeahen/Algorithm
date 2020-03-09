package Programmers;

//하샤드 수 - level1
public class P_12947 {

    public static void main(String[] args) {
        int arr = 12;
        System.out.print(solution(arr));
    }

    public static boolean solution(int x) {

        int n = x;
        int sum = 0;
        while (n != 0) {
            sum += n % 10;
            n = n / 10;

        }

        if (x % sum == 0) {
            return true;
        }

        return false;
    }
}
