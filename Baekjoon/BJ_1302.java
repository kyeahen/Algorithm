package Baekjoon;

import java.util.*;

/**
 * Created by kyeahen.
 * Title : 1302 베스트셀러
 * Category : 정렬, 해시를 사용한 집합과 맵
 * Date: 2021/05/26
 */
public class BJ_1302 {

    static int N;
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        N = s.nextInt();
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String title = s.next();

            if (map.containsKey(title)) {
                map.put(title, map.get(title) + 1);
            } else {
                map.put(title, 1);
            }
        }

        List<String> keySet = new ArrayList<>(map.keySet());
        Collections.sort(keySet); //사전순 오름차순 정렬
        keySet.sort((o1, o2) -> map.get(o2) - map.get(o1)); //판매 수 내림차순 정렬

        System.out.println(keySet.get(0));
    }
}
