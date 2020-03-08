import java.util.Scanner;

public class BJ_2490 {

    private final static int numberOfTest = 3;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int[] inputArr = new int[4];
        String[] outputArr = new String[numberOfTest];

        for (int j = 0; j < numberOfTest; j++) {
            for (int i = 0; i < inputArr.length; i++) {
                inputArr[i] = s.nextInt();
            }

            int back = getCount(inputArr);

            switch (back) {
                case 0 :
                    outputArr[j] = "D";
                    break;

                case 1 :
                    outputArr[j] = "C";
                    break;

                case 2 :
                    outputArr[j] = "B";
                    break;

                case 3 :
                    outputArr[j] = "A";
                    break;

                case 4 :
                    outputArr[j] = "E";
                    break;

                default :
                    break;
            }
        }

        getResult(outputArr);
    }

    //등(1)을 개수를 반환하는 메소드
    public static int getCount(int[] inputArr) {
        int count = 0;
        for (int i = 0; i < inputArr.length; i++) {
            if (inputArr[i] == 1) {
                count++;
            }
        }

        return count;
    }

    //실행 결과를 반환하는 메소드
    public static void getResult(String[] outputArr) {
        for (int i = 0; i < numberOfTest; i++) {
            System.out.println(outputArr[i]);
        }
    }
}
