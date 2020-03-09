package Programmers;

//문자열 내 p와 y의 개수 - level1
public class P_12916 {

    public static void main(String[] args) {

        String s = "pPpoooyY";
        System.out.print(solution(s));
    }

    public static boolean solution(String s) {
        char[] array = s.toCharArray();

        int pCnt = 0;
        int yCnt = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 'p' || array[i] =='P') {
                pCnt++;
            }
            if (array[i] == 'y' || array[i] == 'Y') {
                yCnt++;
            }
        }

        if (pCnt == yCnt) {
            return true;
        } else {
            return false;
        }
    }
}
