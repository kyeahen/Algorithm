package Baekjoon;
import java.util.Scanner;

public class BJ_2577 {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int A = s.nextInt();
        int B = s.nextInt();
        int C = s.nextInt();

        int result = A * B * C;
        String resultToString = String.valueOf(result); //int형 결과를 String형으로 변환한 결과

        int[] numberArr = new int[resultToString.length()]; //계산한 결과를 쪼갠 것을 저장할 배열
        int[] resultArr = new int[10]; //숫자가 몇번 쓰였는지 저장할 배열(0~9)

        //계산한 결과를 쪼개어 배열에 저장하는 함수
        for (int i = 0; i < resultToString.length(); i++) {
            int num = Character.digit(resultToString.charAt(i), 10);
            numberArr[i] = num;
        }

        //숫자가 몇번 쓰였는지 계산하여 배열에 저장하는 함수
        for (int i = 0; i < 10; i++) {

            for (int j = 0; j < numberArr.length; j++) {
                if (numberArr[j] == i) {
                    resultArr[i]++;
                }
            }
        }

        //결과를 출력하는 함수
        for (int i = 0; i < resultArr.length; i++) {
            System.out.println(resultArr[i]);
        }
    }
}
