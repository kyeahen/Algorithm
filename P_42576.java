import java.util.HashMap;

//완주하지 못한 선수 - level1
public class P_42576 {
    public static void main(String[] args) {
        String[] part = {"leo", "kiki", "eden"};
        String[] comp = {"eden", "kiki"};
        System.out.print(solution(part, comp));
    }

    public static String solution(String[] participant, String[] completion) {

        String result = "";
        HashMap<String, Integer> hashMap = new HashMap<>();

        for (String p : participant) {
            if (!hashMap.containsKey(p)) {
                hashMap.put(p, 0);
            } else {
                //동명이인일 때 마다 value 값 증가
                hashMap.put(p, hashMap.get(p) + 1);
            }
        }

        for (String c : completion) {
            hashMap.put(c, hashMap.get(c) - 1);
        }

        for (String m : hashMap.keySet()) {
            if (hashMap.get(m) != -1) {
                result = m;
            }
        }
        return result;
    }6
}
