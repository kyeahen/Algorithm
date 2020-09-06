package Programmers;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//2018 KAKAO Blind Recruitment - [3차] 방금그곡
public class P_17683 {

    public static void main(String[] args) {
        String m = "CC#BCC#BCC#BCC#B";
        String[] music = {"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"};

        String m1 = "ABC";
        String[] music1 = {"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"};

        String m2 = "ABCDEFG";
        String[] music2 = {"12:00,12:14,HELLO,CDEFGAB", "13:00,13:14,WORLD,CDEFGAB"};

        System.out.println(solution(m, music));
    }

    public static String solution(String m, String[] musicinfos) {
        ArrayList<Music> arr = new ArrayList<>();

        for (int i = 0; i < musicinfos.length; i++) {
            String[] info = musicinfos[i].split(",");

            try {
                long time = getTime(info[0], info[1]);
                String title = info[2];
                String m1 = getMelody(m);
                String m2 = getMelody(info[3]);

                if (check(m1, m2, time)) { //멜로디가 일치하면
                    arr.add(new Music(i, time, title)); //배열에 추가
                }

            } catch (ParseException pe) {
                System.out.println("ParseException");
            }
        }

        //정렬: 재생 시간이 제일 긴 음악 -> 먼저 입력된 음악 제목
        Comparator<Music> musicComparator = new Comparator<Music>() {
            @Override
            public int compare(Music o1, Music o2) {

                if (o1.time == o2.time) { //재생 시간이 같으면
                    return o1.index - o2.index; //먼저 입력된 음악을 반환 (오름차순)
                }
                return (int)(o2.time - o1.time); //재생 시간이 제일 긴 음악을 반환 (내림차순)
            }
        };

        String result = "";
        if (arr.size() == 0) { //조건과 일치하는 음악이 없을 때
            result = "(None)";
        } else {
            Collections.sort(arr, musicComparator);
            result = arr.get(0).title; //가장 앞에 정렬된 음악이 조건과 일치하는 음악
        }

        return result;
    }

    //멜로디에서 #을 소문자로 반환하는 메소드
    public static String getMelody(String m) {
        String[] sharp = {"C#", "D#", "E#", "F#", "G#", "A#"};
        String[] lowercase = {"c", "d", "e", "f", "g", "a"};

        String result = m;
        for(int i = 0 ; i < sharp.length ; ++i) {
            result = result.replaceAll(sharp[i], lowercase[i]);
        }

        return result;
    }

    //해당 곡이 조건과 일치하는지 확인하는 메소드
    public static boolean check(String m1, String m2, long time) {
        String melody = "";

        int t = (int)time; //재생 시간
        int turn = t / m2.length(); //전체 멜로디 반복 횟수
        int i = 0;
        while (turn > i) {
            melody += m2; //멜로디 반복
            i++;
        }

        int count = t % m2.length(); //전체 멜로디 반복 후, 남은 시간
        if (count != 0) { //시간이 남았으면 시간만큼 멜로디 반복
            melody += m2.substring(0, count);
        }

        if (melody.contains(m1)) { //해당 곡이 네오의 멜로디를 포함하면
            return true; //조건과 일치하기에 true 반환
        }

        return false;
    }

    //재생 시간을 반환하는 메소드
    public static long getTime(String start, String end) throws ParseException{
        SimpleDateFormat transFormat = new SimpleDateFormat("HH:mm");

        Date startTime = transFormat.parse(start);
        Date endTime = transFormat.parse(end);
        long minute = (endTime.getTime() - startTime.getTime()) / 60000;

        return minute;
    }
}

class Music {
    int index; //배열 index
    long time; //재생 시간
    String title; //음악 제목

    public Music(int index, long time, String title) {
        this.index = index;
        this.time = time;
        this.title = title;
    }
}
