package Programmers;

//2018 KAKAO Blind Recruitment - [3차] n진수 게임
public class P_17687 {

    public static void main(String[] args) {
        System.out.println(solution(16, 16, 2, 2));
    }

    public static String solution(int n, int t, int m, int p) {
        String result = "";

        String total = "";
        for (int i = 0; i < t * m; i++) { //전체 숫자 구하기
            String sNum = getNumber(n, i);
            total += sNum;
        }

        int idx = p - 1; //튜브의 순서
        for (int i = 0; i < t; i++) { //튜브의 순서에 맞는 숫자 구하기
            result += total.charAt(idx);
            idx += m; //다음 튜브의 턴으로 넘어가기
        }

        return result;
    }

    //n진수를 구하는 메소드
    public static String getNumber(int n,int num) { // n: n진법, num: 숫자
        char[] c = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

        String r = "";
        while (num / n != 0) { //몫이 0이 될 때까지 반복
            int a = num % n;
            r = c[a] + r; //나머지가 a일 때, a번째 숫자(c[a])를 앞에 계속 추가 (나머지들의 역순이므로)
            num = num / n;
        }
        r = c[num % n] + r; //몫이 0일 때, 마지막 나머지를 추가

        return r;
    }
}
