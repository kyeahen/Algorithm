package Programmers;

//2016년 - level1
public class P_12901 {

    public static void main(String[] args) {
        int a = 5;
        int b = 24;

        System.out.print(solution(a, b));
    }

    public static String solution(int a, int b) {
        String[] day = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        int[] month = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int monthSum = 0;
        for (int i = 0; i < a - 1; i++) {
            monthSum += month[i];
        }

        return day[(monthSum + b + 4) % 7];
    }
}
