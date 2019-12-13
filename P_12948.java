//핸드폰 번호 가리기 - level1
public class P_12948 {
    public static void main(String[] args) {
        String phone_number = "01033334444";
        System.out.print(solution(phone_number));
    }

    public static String solution(String phone_number) {
        char[] numArr = phone_number.toCharArray();

        int count = 1;
        for (int i = numArr.length - 1; i >= 0; i--) {
            if (count > 4) {
                numArr[i] = '*';
            }
            count++;
        }

        return new String(numArr);
    }
}
