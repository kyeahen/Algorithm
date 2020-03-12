package Programmers;
import java.util.Arrays;

//최댓값과 최솟값 - level2
public class P_12939 {

    public static void main (String[] args) {
        String s = "-1 -1";

        System.out.println(solution(s));
    }

    public static String solution (String s) {
        String[] strArr = s.split(" "); //공백을 기준으로 문자열을 분해하여 배열로 저장

        int[] numArr = new int[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            numArr[i] = Integer.parseInt(strArr[i]); //문자열을 int형으로 변환하여 저장
        }

        Arrays.sort(numArr); //오름차순 정렬

        return numArr[0] + " " + numArr[numArr.length - 1]; //최솟값, 최댓값 반환
    }
}
