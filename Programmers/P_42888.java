package Programmers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

//2019 kakao blind recruitment - 오픈채팅방
public class P_42888 {

    static String ENTER = "Enter";
    static String LEAVE = "Leave";

    public static void main(String[] args) {
        String[] array = {"Enter uid1234 Muzi", "Enter uid4567 Prodo",
                "Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};

        System.out.println(Arrays.toString(solution(array)));
    }

    public static String[] solution(String[] record) {
        ArrayList<String> result = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();

        for (int i = 0; i < record.length; i++) {
            String[] user = record[i].split(" ");
            String status = user[0];
            String id = user[1];

            if (!status.equals(LEAVE)) {
                String nick = user[2];
                map.put(id, nick) ; //id, nick
            }
        }

        for (int i = 0; i < record.length; i++) {
            String[] user = record[i].split(" ");
            String status = user[0];
            String id = user[1];

            if (status.equals(ENTER)) {
                result.add(map.get(id) + "님이 들어왔습니다.");

            } else if (status.equals(LEAVE)) {
                result.add(map.get(id) + "님이 나갔습니다.");
            }
        }

        return result.toArray(new String[result.size()]);
    }
}
