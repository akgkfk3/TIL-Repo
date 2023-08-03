package sesac.java.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Quiz09 {

    public static void main(String[] args) throws IOException {
        
        // Random 객체 생성
        Random rd = new Random();

        // 크기 4인 정수형 배열 2개 생성
        int[] playerArr = new int[4];
        int[] computerArr = new int[4];

        // 배열 데이터 초기화
        for (int i=0; i<4; i++) {
            playerArr[i] = rd.nextInt(10) + 1;
            computerArr[i] = rd.nextInt(10) + 1;
        }

        System.out.println("카드가 분배됩니다.");
        IntStream stream = Arrays.stream(playerArr);
        stream.forEach(x -> System.out.print(x + " "));
        System.out.println("두 개의 카드를 선택해주세요.");

        // 2개의 카드 순서입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int userSum = playerArr[arr[0]] + playerArr[arr[1]];

        // 컴퓨터 4개의 카드 중 가장 높은값 2장의 카드 뽑아내기
        int firstMax = 0;
        int secondMax = 0;

        for(int i=0; i<2; i++) {
            for(int j=0; j<computerArr.length; j++) {
                if (i == 0) {
                    firstMax = (firstMax < computerArr[j]) ? computerArr[j] : firstMax;
                } else {
                    secondMax = (firstMax > computerArr[j] && secondMax < computerArr[j])
                            ? computerArr[j] : secondMax;
                }
            }
        }

        int computerSum = firstMax + secondMax;

        if (userSum > computerSum) {
            System.out.println(userSum + " vs " + computerSum + " 유저의 승리 입니다.");
        } else if (userSum == computerSum) {
            System.out.println("동점");
        } else {
            System.out.println(userSum + " vs " + computerSum + " 컴퓨터의 승리 입니다.");
        }
    }
}
