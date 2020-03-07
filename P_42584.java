import java.util.Arrays;

//주식가격 - level2
public class P_42584 {

    public static void main(String[] args) {
        int[] prices = {1, 2, 3, 2, 3};
        System.out.print(Arrays.toString(solution(prices)));
    }

    public static int[] solution(int[] prices) {
        int[] resultArr = new int[prices.length];

        for (int i = 0; i < prices.length; i++) {
            int count = 0;
            for (int j = i + 1; j < prices.length; j++) {
                count += 1;

                //주식 가격이 떨어지면 반복문 탈출
                if (prices[i] > prices[j]) {
                    break;
                }
            }

            resultArr[i] = count;
        }

        return resultArr;
    }
}
