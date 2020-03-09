package Programmers;
import java.util.Arrays;

//전화번호 목록 - level2
public class P_42577 {

    public static void main(String[] args) {
        String[] phone_book = {"119", "97674223", "1195524421"};
        System.out.print(solution(phone_book));
    }

    public static boolean solution(String[] phone_book) {

        Arrays.sort(phone_book);

        boolean result = true;
        for (int i = 0; i < phone_book.length - 1; i++) {
            if (phone_book[i + 1].startsWith(phone_book[i])) {
                result = false;
            }
        }

        return result;
    }
}
