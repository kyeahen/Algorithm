package Programmers;

//다음 큰 숫자 - level2
public class P_12911 {

    public static void main(String[] args) {
        int n = 15;

        System.out.print(solution(n));
    }

    public static int solution(int n) {

        int i = n;
        while (true) {
            i++;

            int binN = getOneCount(Integer.toBinaryString(n)); //n의 1의 갯수
            int binI = getOneCount(Integer.toBinaryString(i)); //i의 1의 갯수

            if (n < i && binN == binI) {
                break;
            }
        }

        return i;
    }

    //1의 갯수를 반환하는 함수
    public static int getOneCount(String bin) {

        int count = 0;
        for (int i = 0; i < bin.length(); i++) {
            if (bin.charAt(i) == '1') {
                count++;
            }
        }

        return count;
    }
}
