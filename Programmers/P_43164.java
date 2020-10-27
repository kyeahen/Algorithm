package Programmers;
import java.util.*;

//여행경로 - level3 (bfs)
//테스트 케이스 1번만 통과 못한 풀이
class Data implements Comparable<Data> {

    String name; //경로 이름
    int index; //기존 배열 index

    public Data(String name, int index) {
        this.name = name;
        this.index = index;
    }

    @Override
    public int compareTo(Data data) { //오름차순 정렬
        return name.compareToIgnoreCase(data.name);
    }

    @Override
    public String toString() {
        return "Data{" +
                "name='" + name + '\'' +
                ", index=" + index +
                '}';
    }
}

public class P_43164 {

    static ArrayList<String> r = new ArrayList<>();

    public static void main(String[] args) {
        String[][] t = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};
        String[][] t1 = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        String[][] t2 = {{"ICN", "A"}, {"ICN", "B"}, {"B", "ICN"}};
        String[][] t3 = {{"ICN", "A"}, {"A", "C"}, {"A", "D"}, {"D", "B"}, {"B", "A"}};
        String[][] t4 = {{"ICN","A"},{"ICN","B"},{"B","ICN"}};
        String[][] t5 = {{"ICN","A"},{"ICN","B"}};
        String[][] t6 = {{"ICN","A"},{"A","B"},{"B","A"},{"A","ICN"},{"ICN","A"}};

        System.out.println(Arrays.toString(solution(t5)));
    }

    public static String[] solution(String[][] tickets) {

        bfs(tickets, "ICN");

        String[] result = new String[r.size()];
        result = r.toArray(result);

        return result;
    }

    public static void bfs(String[][] tickets, String start) {
        Queue<String> q = new LinkedList<>();
        boolean[] visited = new boolean[tickets.length];

        q.add(start);
        r.add(start);

        while (!q.isEmpty()) {
            String s = q.poll();

            ArrayList<Data> list = new ArrayList<>();
            String start1 = "", depart1 = "";
            for (int i = 0; i < tickets.length; i++) {
                start1 = tickets[i][0];
                depart1 = tickets[i][1];

                if (!visited[i] && s.equals(start1)) {

                    //사용하지 않은 티켓 중 다음 경로가 있으면 추가
                    if (check(visited, tickets, depart1)) {
                        list.add(new Data(depart1, i)); //경로에 맞는 티켓 추가
                    } else {
                        if (r.size() == tickets.length) { //마지막 티켓인 경우
                            list.add(new Data(depart1, i)); //경로에 맞는 티켓 추가
                        }
                    }
                }
            }

            //다음 경로가 있는 경우
            if (list.size() != 0) {
                Collections.sort(list); //알파벳순 정렬

                visited[list.get(0).index] = true; //방문 체크
                q.add(list.get(0).name); //다음 출발지 추가
                r.add(list.get(0).name); //결과 배열에 경로 추가
            }

        }
    }

    //다음 경로의 티켓이 존재하는지 확인하는 메소드
    public static boolean check(boolean[] visited, String[][] tickets, String depart) {

        int count = 0;
        for (int i = 0; i < tickets.length; i++) {
            String s = tickets[i][0];
            if (depart.equals(s) && !visited[i]) {
                count += 1;
            }
        }

        if (count == 0) { //다음 경로의 티켓이 없는 경우 - false
            return false;
        }

        return true;
    }
}