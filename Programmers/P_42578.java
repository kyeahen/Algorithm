package Programmers;
import java.util.HashMap;

//위장 - level2
public class P_42578 {

    public static void main(String[] args) {
        String[][] arr = {{"yellow_hat", "headgear"},
                        {"blue_sunglasses", "eyewear"},
                        {"green_turban", "headgear"}};

        System.out.print(solution(arr));
    }

    public static int solution(String[][] clothes) {
        HashMap<String, Integer> hm = new HashMap<>();
        int count = 1;

        for (int i = 0; i < clothes.length; i++) {

            //이미 존재하는 키(의상 카테고리)가 아니라면 해쉬맵에 저장
            if (!hm.containsKey(clothes[i][1])) {
                hm.put(clothes[i][1], 1); // (의상 카테고리, 동일 의상 카테고리 개수)
            } else { //이미 존재하는 키라면 기존 해쉬맵 value 값 갱신 (+1)
                hm.put(clothes[i][1], hm.get(clothes[i][1]) + 1);
            }
        }

        //경우의 수 구하기
        /* 예시) : (headgear 개수 + 1) * (eyewear 개수 + 1) - 1
        동일 의상 카테고리 개수에 1을 더해주는 이유는 해당 카테고리의 의상을 입지 않는 경우도 있기 때문이다.
        마지막에 -1을 해주는 이유는 최소 1개의 의상은 입기 때문이다.
        -1을 해주지 않으면 의상을 하나도 입지 않은 경우도 나온다.
        */
        for (String key : hm.keySet()) {
            count *= hm.get(key) + 1;
        }

        return count - 1;
    }
}
