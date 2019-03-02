import java.util.Scanner;

public class BJ_2675 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int testNum = s.nextInt();

        for (int i = 0; i < testNum; i++) {
            int alphaNum = s.nextInt();
            String[] charArr = new String[alphaNum];
            String alpha = s.next();
            charArr = alpha.split(""); //배열에 한글자씩 저장하

            for (int k = 0; k < charArr.length; k++) {
                for (int a = 0; a < alphaNum; a++) {
                    System.out.print(charArr[k]);
                }
            }
            System.out.println();
        }
    }
 }
