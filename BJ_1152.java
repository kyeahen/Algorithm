import java.util.Scanner;

public class BJ_1152 {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        String input = s.nextLine();

        String[] resultArr;
        resultArr = input.split(" ");

        int output = resultArr.length;
        for (int i = 0; i < resultArr.length; i++) {

            if (resultArr[i].equals("")) {
                output--;
            }
        }

        System.out.print(output);
    }
}
