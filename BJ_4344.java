import java.util.Scanner;
public class BJ_4344 {

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);

        int caseNum = s.nextInt(); // 테스트 케이스 수

        for (int i = 0; i < caseNum; i++) {

            int studentNum = s.nextInt(); //학생의 수
            int[] scoreArr = new int[studentNum]; //학생들의 점수

            for (int j = 0; j < studentNum; ++j) {
                scoreArr[j] = s.nextInt();
            }

            double average = getAvarage(scoreArr);
            double result = getAverageUpPercent(average, scoreArr);

            System.out.printf("%.3f", result);
            System.out.println("%");
        }
    }

    //학생들의 평균 점수를 구하는 메소드
    public static double getAvarage(int[] arr) {

        int total = 0;
        for(int i = 0; i < arr.length; i++) {
            total += arr[i];
        }

        double average = (double) total / arr.length;
        return average;
    }

    //평균을 넘는 학생들의 비율을 구하는 메소드
    public static double getAverageUpPercent(double average, int[] arr) {

        int check = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > average) {
                check++;
            }
        }

        double percent = (double) (check * 100) / arr.length;
        return percent;
    }

}
