package sesac.java.practice;

import java.io.IOException;
import java.util.Scanner;

public class Quiz02 {

    public static void main(String[] args) throws IOException {

        // Quiz_02
        Scanner sc = new Scanner(System.in);

        System.out.println("가위바위보 게임입니다. 가위:1, 바위:2, 보:3");
        System.out.println("a 유저의 번호를 선택해주세요");
        int a = sc.nextInt();

        System.out.println("b 유저의 번호를 선택해주세요");
        int b = sc.nextInt();

        if (a-b == -1 || a-b == 2)
            System.out.println("b 승리");
        else if ( a - b == 0)
            System.out.println("비김");
        else
            System.out.println("a 승리");
    }
}
