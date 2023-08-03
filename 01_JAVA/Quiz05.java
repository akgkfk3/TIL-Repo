package sesac.java.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Quiz05 {

    public static void main(String[] args) throws IOException {

        // Quiz_05
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            System.out.println("메뉴 개수 입력");
            String[] menu_Count = br.readLine().split(" ");

            String menu = menu_Count[0];
            int count = menu_Count.length > 1 ? Integer.parseInt(menu_Count[1]) : 0;
            int price = 0;

            if (menu.equals("종료")) {
                System.out.println("프로그램 종료");
                break;
            } else if (!(Menu.menuMap.containsKey(menu))) {
                System.out.println("메뉴가 없습니다.");
                continue;
            }

            // 메뉴 가격 초기화
            price = Menu.menuMap.get(menu);

            System.out.println("총 가격은 "+ (price * count) + "원입니다.");
            int money = Integer.parseInt(br.readLine());
            money -= price * count;

            // 지불금액 체크
            if (money >= 0) {
                System.out.println("잔돈 " + money + "원입니다");
                for (int i=0; i<count; i++)
                    System.out.println(menu + " 나왔습니다");
            } else {
                System.out.println("금액이 부족합니다");
            }
        }
    }
}

// Menu 클래스
class Menu {

    public static Map<String, Integer> menuMap = new ConcurrentHashMap<>();

    // menuMap 초기화
    static {
        menuMap.put("딸기요거트", 4500);
        menuMap.put("밀크티", 3500);
        menuMap.put("카페라떼", 4500);
        menuMap.put("아메리카노", 2000);
    }
}
