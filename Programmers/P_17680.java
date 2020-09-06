package Programmers;
import java.util.LinkedList;
import java.util.Queue;

//2018 KAKAO Blind Recruitment - [1차] 캐시
public class P_17680 {

    public static void main(String[] args) {
        int size = 0;
        String[] arr1 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        String[] arr2 = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
        String[] arr3 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        String[] arr4 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};
        String[] arr5 = {"Jeju", "Pangyo", "NewYork", "newyork"};
        String[] arr6 = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        System.out.println(solution(size, arr6));
    }

    //LRU 알고리즘 : 페이지 제거시 가장 오랫동안 사용하지 않은 것을 제거
    // hit : CPU가 참조하고자 하는 메모리가 캐시에 존재하고 있을 경우
    // miss : CPU가 참조하고자 하는 메모리가 캐시에 존재하지 않을 때
    public static int solution(int cacheSize, String[] cities) {
        Queue<String> q = new LinkedList<>();

        if (cacheSize == 0) { //캐시 사이즈가 0인 경우
            return 5 * cities.length;
        }

        int i = 0, size = 1, time = 0;
        while (i < cities.length) {
            String city = cities[i].toLowerCase(); //대소문자 구분 X

            if (size <= cacheSize) { //캐시 사이즈가 꽉차지 않은 경우
                if (!q.contains(city)) { //캐시에 없는 데이터면 큐에 삽입
                    q.add(city);
                    size++; //캐시 사이즈 증가
                    time += 5;
                } else { //캐시에 존재하면 순서 변경
                    q.remove(city);
                    q.add(city);
                    time++;
                }

            } else { //캐시 사이즈가 꽉찬 경우
                if (!q.contains(city)) { //캐시에 없는 데이터일 때
                    q.poll(); //가장 오랫동안 사용하지 않은 데이터 제거
                    q.add(city);
                    time += 5;
                } else { //캐시에 존재하면 순서 변경
                    q.remove(city);
                    q.add(city);
                    time++;
                }

            }

            i++;
        }

        return time;
    }
}
