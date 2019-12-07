import java.util.Scanner;

//문자열을 정수로 바꾸기 - level1
public class P_12925 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        String num = s.next();
        System.out.print(solution(num));
    }

    public static int solution(String s) {
        return Integer.parseInt(s);
    }
}
