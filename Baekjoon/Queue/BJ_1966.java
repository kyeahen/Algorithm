package Baekjoon.Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by kyeahen.
 * Title : 1966 프린터큐
 * Category : 구현, 시뮬레이션, 큐
 * Date: 2021/04/30
 */
public class BJ_1966 {

    static class Document {
        int index; //문서 index
        int importance; //중요도

        Document (int index, int importance) {
            this.index = index;
            this.importance = importance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); //문서의 개수
            int M = Integer.parseInt(st.nextToken()); //몇번째로 인쇄되었는지 궁금한 문서의 인덱스

            /*
            [Queue가 아닌 LinkedList를 사용한 이유]
            : 문서를 출력할 때, 전체 문서의 중요도를 체크하는 일이 필요한데
            Queue는 q.get(i)를 사용할 수 없다.
            LinkedList는 사용할 수 있기 때문에 LinkedList를 사용했다.
             */
            LinkedList<Document> q = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int a = Integer.parseInt(st.nextToken());
                q.offer(new Document(i, a));
            }

            int count = 0;
            while (!q.isEmpty()) {
                Document doc = q.poll();

                boolean flag = true; //문서 출력 여부
                for (int i = 0; i < q.size(); i++) {
                    if (doc.importance < q.get(i).importance) { //현재 문서보다 중요도 높은 문서가 있으면
                        q.offer(doc); //인쇄하지 않고 가장 뒤에 배치
                        flag = false;
                        break;
                    }
                }

                if (flag) { //문서 출력
                    count++;

                    if (doc.index == M) { //내가 궁금한 문서일 때, 순서 출력
                        System.out.println(count);
                        break;
                    }
                }
            }

        }
    }
}
