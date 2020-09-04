package Programmers;
import java.util.*;

//2018 KAKAO Blind Recruitment - [3차] 파일명 정렬
public class P_17686 {

    public static void main(String[] args) {
        String[] files = {"fooaad0110100bar020.zip", "img12.png", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"};
        String[] files2 = {"F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat"};
        String[] files3 = {"muzi1.txt", "MUZI1.txt", "muzi001.txt", "muzi1.TXT"};
        String[] files4 = {"F15", "F11", "F03", "F1", "F 0"};
        String[] files5 = {"img111111.png", "img11112.png", "img0101.png", "img1.png", "IMG01.GIF", "img2.JPG"};

        System.out.println(Arrays.toString(solution(files5)));
    }

    public static String[] solution(String[] files) {

        String[] result = new String[files.length];
        ArrayList<File> data = new ArrayList<>();

        for (int i = 0; i < files.length; i++) {
            String head = "";
            String num = "";

            int numIdx = 0;
            for (int j = 0; j < files[i].length(); j++) {
                char c = files[i].charAt(j);

                if (Character.isDigit(c)) { //숫자이면
                    head = files[i].substring(0, j).toLowerCase(); //숫자 이전은 head
                    numIdx = j; //숫자 시작 index
                    break;
                }
            }

            for (int j = numIdx; j < files[i].length(); j++) {
                char c = files[i].charAt(j);

                if (Character.isDigit(c)) { //숫자이면
                    num += c; //숫자 저장
                } else {
                    break;
                }

                // "NUMBER는 한 글자에서 최대 다섯 글자 사이의 연속된 숫자"
                if (num.length() == 5) {
                    break;
                }
            }

            data.add(new File(head, Integer.parseInt(num), files[i], i));
        }

        //정렬:  head 기준 -> 숫자 기준 -> 원래 입력 순서
        Comparator<File> filesComparator = new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {

                if (o1.head.equals(o2.head)) {
                    if (o1.num == o2.num) {
                        return o1.idx - o2.idx;
                    }

                    return o1.num - o2.num;
                }
                return o1.head.compareTo(o2.head);
            }
        };

        Collections.sort(data, filesComparator);

        for (int i = 0; i < data.size(); i++) {
            result[i] = data.get(i).full;
        }

        return result;
    }
}

class File {
    int idx; //원 배열 index
    String head;
    int num;
    String full; //파일명

    public File(String head, int num, String full, int idx) {
        this.head = head;
        this.num = num;
        this.idx = idx;
        this.full = full;
    }
}