package sesac.java.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz01 {

    public static void main(String[] args) throws IOException {
        
        // Quiz_01
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("음료 메뉴 입력");
        String menu = br.readLine();

        int price = 0;

        switch(menu) {
            case "딸기요거트":
                System.out.print("딸기요거트는 4500원입니다");
                price = 4500;
                break;
            case "밀크티":
                System.out.print("밀크티는 3500원입니다");
                price = 3500;
                break;
            case "카페라떼":
                System.out.print("카페라떼는 3500원입니다");
                price = 3500;
                break;
            case "아메리카노":
                System.out.print("아메리카노는 2000원입니다");
                price = 2000;
                break;
            default:
                System.out.println("메뉴가 없습니다");
        }
        int money = Integer.parseInt(br.readLine());

        if (price <= money)
            System.out.println("잔돈 " + (money - price) + "원입니다");
        else
            System.out.println("금액이 부족합니다.");
    }
}
