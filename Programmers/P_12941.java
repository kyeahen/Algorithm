package Programmers;
import java.util.Arrays;

//최솟값 만들기 - level2
public class P_12941 {

    public static void main(String[] args) {
        int[] A = {1, 2};
        int[] B = {3, 4};

        System.out.print(solution(A, B));
    }

    public static int solution(int[] A, int[] B) {
        int[] resultArr = new int[A.length];

        Arrays.sort(A); //A 오름차순 정렬

        Arrays.sort(B);
        int[] newBArr = new int[B.length];
        for (int i = 0; i < newBArr.length; i++) { //B 내림차순 정렬
            newBArr[i] = B[B.length - i - 1];
        }

        for (int i = 0; i < A.length; i++) {
            resultArr[i] = A[i] * newBArr[i];
        }

        int total = 0;
        for (int i = 0; i < resultArr.length; i++) {
            total += resultArr[i];
        }

        return total;
    }
}
