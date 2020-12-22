package Baekjoon.Greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//멀티탭 스케쥴링
public class BJ_1700 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        int k = s.nextInt();

        int[] use = new int[k];
        HashMap<String, Integer> map = new HashMap<>();

        for (int i = 0; i < k; i++) {
            use[i] = s.nextInt();

            String key = String.valueOf(use[i]);
            if (!map.containsKey(use[i])) {
                map.put(key, 1);
            } else {
                map.put(key, map.get(use[i]) + 1);
            }
        }

        ArrayList<String> list = new ArrayList<>();

        int count = 0;
        for (int i = 0; i < k; i++) {
            String current = String.valueOf(use[i]);

            System.out.println(list.toString());
            if (list.size() == n) {

                String pick = "";
                for (String l : list) {
                    if (map.get(l) == 0) { //뒤에 이제 안나오는 것
                        pick = l;
                    } else { //가장 마지막에 쓸 용품

                        for (int j = i + 1; j < k; j++) {
                            int il = Integer.valueOf(l);

                            if (il == use[j]) {
                                pick = l;
                            }
                        }
                    }
                }

                list.remove(pick);
                count++;
            }

            if (!list.contains(current)) {
                list.add(current);
            }

            map.put(current, map.get(current) - 1);
        }

        System.out.println(count);
    }



}
