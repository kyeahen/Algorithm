//시저 암호 - level1
public class P_12926 {

    public static void main(String[] args) {
        String s = "a B z";
        int n = 4;

        System.out.print(solution(s, n));
    }

    public static String solution(String s, int n) {

        char[] arr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (arr[i] != ' ') {
                int a = (int) arr[i] + n;
                char c = (char) a;

                if (Character.isLowerCase(arr[i])) {
                    if (c > 'z') {
                        arr[i] = (char) (a - 26);
                    } else {
                        arr[i] = c;
                    }
                } else {
                    if (c > 'Z') {
                        arr[i] = (char) (a - 26);
                    } else {
                        arr[i] = c;
                    }
                }
            }
        }
        return new String(arr);
    }
}
