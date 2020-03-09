package Baekjoon;
import java.util.Scanner;

//경로 찾기
public class BJ_11403 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();

        int[][] inputArr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inputArr[i][j] = s.nextInt();
            }
        }


    }

}
