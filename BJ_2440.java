import java.util.Scanner;

public class BJ_2440 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int num = s.nextInt();

        for (int i = num; i > 0; i--) {
            for (int j = i; j > 0; j--) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}