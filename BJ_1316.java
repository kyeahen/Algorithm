import java.util.Scanner;

public class BJ_1316 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int num = s.nextInt();

        int count = num;
        for (int i = 0; i < num; i++) {
            String word = s.next();
            boolean[] check = new boolean[26];

            for (int j = 1; j < word.length(); j++) {
                if (word.charAt(j - 1) != word.charAt(j)) {

                    if (check[word.charAt(j) - 97] == true) {
                        count--;
                        break;
                    }
                    check[word.charAt(j - 1) - 97] = true;
                }
            }
        }

        System.out.print(count);
    }
}
