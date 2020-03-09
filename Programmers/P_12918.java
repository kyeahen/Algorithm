package Programmers;

//문자열 다루기 기본 - level1
public class P_12918 {

    public static void main(String[] args) {
        String s = "a123";
        System.out.print(solution1(s));
    }

    //isDigit 함수를 이용한 방법
    public static boolean solution1(String s) {
        char[] array = s.toCharArray();

        boolean check = true;
        if (array.length != 4 && array.length != 6) {
            check = false;
        }

        for (int i = 0; i < array.length; i++) {
            if (!Character.isDigit(array[i])) {
                check = false;
            }
        }

        return check;
    }

    //try-catch를 이용한 방법
    public static boolean solution2(String s) {
        if (s.length() == 4 || s.length() == 6) {
            try {
                Integer.parseInt(s);
                return true;
            } catch (NumberFormatException ne) {
                return false;
            }
        }

        return false;
    }

    //숫자의 범위를 이용한 방법
    public static boolean solution3(String s) {
        char[] array = s.toCharArray();

        boolean check = true;
        if (array.length != 4 && array.length != 6) {
            check = false;
        }

        for (int i = 0; i < array.length; i++) {
            if ('0' < array[i] && array[i] > '9') {
                check = false;
            }
        }

        return check;
    }
}
