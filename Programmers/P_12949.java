package Programmers;

//행렬의 곱셈 - level2
public class P_12949 {

    public static void main(String[] args) {
        int[][] arr1 = {{1, 4}, {3, 2}, {4, 1}};
        int[][] arr2 = {{3, 3}, {3, 3}};
    }

    public static int[][] solution(int[][] arr1, int[][] arr2) {

        int[][] resultArr = new int[arr1.length][arr2[0].length];

        for (int i = 0; i < resultArr.length; i++) {
            for (int j = 0; j < arr1[0].length; j++) {
                for (int k = 0; k < arr2[0].length; k++) {
                    resultArr[i][k] += arr1[i][j] * arr2[j][k];
                }
            }
        }

        return resultArr;
    }
}
