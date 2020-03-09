package Programmers;

//평균 구하기 - level1
public class P_12944 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        System.out.print(solution(arr));
    }

    public static double solution(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }

        double average = (double) sum / arr.length;
        return average;
    }
}
