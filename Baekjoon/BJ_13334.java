package Baekjoon;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

//철로 - 알고리즘 스터디(공통)
// - 우선순위 큐
// https://blog.naver.com/occidere/221085858307

class Line implements Comparable<Line> {
    int start; //왼쪽 점
    int end; //오른쪽 점

    Line(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Line o) { //오른쪽 점 기준으로 오름차순 정렬
        return Integer.compare(this.end, o.end);
    }
}

public class BJ_13334 {

    static int n, d;
    static ArrayList<Line> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        n = s.nextInt(); //사람 수

        for (int i = 0; i < n; i++) {
            int a = s.nextInt(); //왼쪽 점 (집)
            int b = s.nextInt(); //오른쪽 점 (사무실)

            //항상 집이 작은 값을 가지는 것이 아니기에 대소 관계를 확인하여 넣어줘야함
            if (a > b) { //오른쪽 점에 큰 값이 들어가야함.
                int temp = a;
                a = b;
                b = temp;
            }

            list.add(new Line(a, b));
        }

        d = s.nextInt(); //철로 길이
        Collections.sort(list); //오른쪽 점 기준으로 오름차순 정렬

        PriorityQueue<Integer> pq = new PriorityQueue<>(); //최소 힙

        int count = 0;
        int max = 0;
        for (Line l : list) {

            //철로의 길이를 초과하는 선분이 있으면 제거
            // - (오른쪽 점과 철도의 길이의 차이)가 (시작점)보다 크면 철도의 길이를 초과한다는 의미...
            // ? 이것이 잘 이해가 가지 않는다..
            while (!pq.isEmpty() && pq.peek() < l.end - d) {
                pq.poll();
                count--; //철로 포함 사람수 카운트 감소
            }

            if (l.start >= l.end - d) { //철로 사이에 있으면 우선순위 큐에 시작점으로 삽입
                pq.add(l.start);
                count++; //철로 포함 사람수 카운트 증가
            }

            max = Math.max(max, count); //철로에 포함되는 사람의 최대 수
        }

        System.out.println(max);
    }
}

