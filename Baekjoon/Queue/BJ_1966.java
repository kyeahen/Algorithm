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
        int index;
        int importance;

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

            LinkedList<Document> q = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int a = Integer.parseInt(st.nextToken());
                q.offer(new Document(i, a));
            }

            int count = 0;
            while (!q.isEmpty()) {
                Document doc = q.poll();

                boolean flag = true;
                for (int i = 0; i < q.size(); i++) {
                    if (doc.importance < q.get(i).importance) { //현재 문서보다 중요도 높은 문서가 있으면
                        q.offer(doc); //인쇄하지 않고 가장 뒤에 배치
                        flag = false;
                        break;
                    }
                }

                if (flag) { //문서 출력
                    count++;

                    if (doc.index == M) {
                        System.out.println(count);
                        break;
                    }
                }
            }

        }
    }
}
