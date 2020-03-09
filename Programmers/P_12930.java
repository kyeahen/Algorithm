package Programmers;

//이상한 문자 만들기 - level1
public class P_12930 {
    public static void main(String[] args) {
        String s = "try hello world";
        System.out.print(solution(s));
    }

    public static String solution(String s) {
        char[] strArr = s.toCharArray();

        int count = 0;
        for (int i = 0; i <strArr.length; i++) {
            if (strArr[i] == ' ') {
                count = 0;
            }  else {
                if (count % 2 == 0) {
                    strArr[i] = Character.toUpperCase(strArr[i]);
                } else {
                    strArr[i] = Character.toLowerCase(strArr[i]);
                }
                count++;
            }
        }

        return new String(strArr);
    }
}