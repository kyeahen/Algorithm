import java.util.Scanner;

public class BJ_2908 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int num1 = s.nextInt();
        int num2 = s.nextInt();

        String strNum1 = String.valueOf(num1);
        String strNum2 = String.valueOf(num2);

        if (reverseNum(strNum1) > reverseNum(strNum2)) {
            System.out.print(reverseNum(strNum1));
        } else {
            System.out.print(reverseNum(strNum2));
        }
    }

    public static int reverseNum(String num) {

        String reverseNum = "";
        for (int i = num.length() - 1; i >= 0; i--) {
            reverseNum += Character.digit(num.charAt(i), 10);
        }

        return Integer.parseInt(reverseNum);
    }
}
