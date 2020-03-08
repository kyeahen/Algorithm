import java.util.Scanner;

public class BJ_2546 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int subjectNum = s.nextInt(); //과목 수
        int[] scoreArr = new int[subjectNum]; //점수를 저장하는 배열
        double[] newScoreArr = new double[scoreArr.length]; //조작된 점수를 저장하는 배열

        for (int i = 0; i < subjectNum; i++) {
            scoreArr[i] = s.nextInt();
        }

        int max = scoreArr[0];
        for (int i = 0; i < scoreArr.length; i++) {

            if (max < scoreArr[i]) {
                max = scoreArr[i];
            }
        }

        for (int i = 0; i < scoreArr.length; i++) {

            newScoreArr[i] = getNewScore(scoreArr[i], max);
        }

        System.out.print(getAverage(newScoreArr));

    }

    //점수들의 평균을 구하는 메소드
    public static double getAverage(double[] arr) {

        double sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        double average = sum / arr.length;

        return average;
    }

    //점수를 조작하는 메소드
    public static double getNewScore(double origin, int max) {
        return origin / max * 100;
    }

}
