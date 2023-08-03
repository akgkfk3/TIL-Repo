package sesac.java.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Quiz11 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 플레이어 수, 사다리 층수 입력
        System.out.println("플레이어 수와 사다리 층수를 입력해 주세요.");
        String[] input = br.readLine().split(" ");

        int playerNum = Integer.parseInt(input[0]);     // 플레이어 수
        int ladderNum = Integer.parseInt(input[1]);     // 사다리 층수

        // 플레이어 등수 입력 -> stream 사용하여 Array로 Return
        System.out.println("플레이어 수만큼 등수를 정해주세요.");
        int[] rank = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();

        // 사다리 생성
        Ladder ladder = new Ladder(new int[ladderNum][playerNum], rank);

        // 사다리 타기 !!
        System.out.println("----------결 과----------");
        Player player = new Player();
        for (int i=0; i<playerNum; i++) {
            player.play(ladder, i);
        }
    }
}



