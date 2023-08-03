package sesac.java.practice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Quiz06 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("키오스크 키를 입력해주세요.");
        int key = Integer.parseInt(br.readLine());

        if (key == Kiosk.key) {
            System.out.println("매장 재고개수를 입력해주세요.");
            int inventoryCount = Integer.parseInt(br.readLine());
            Kiosk kiosk = new Kiosk(inventoryCount);

            while (true) {
                System.out.println("메뉴와 개수를 입력해주세요.");

                String[] menuCount = br.readLine().split(" ");
                String orderMenu = menuCount[0];

                if (orderMenu.equals("종료")) {
                    System.out.println("프로그램을 종료 합니다.");
                    break;
                }

                int orderCount = Integer.parseInt(menuCount[1]);

                if (!kiosk.isInventory(orderCount)) {
                    System.out.println("재고가 부족합니다.");
                    continue;
                }

                Order order = kiosk.initOrder(orderMenu, orderCount);

                if (order != null) {
                    System.out.println(order.getOrderPrice() + "원 입니다.");
                    int money = Integer.parseInt(br.readLine());

                    if (order.runOrder(money)) {
                        kiosk.subInventroy(orderCount);
                    }
                } else {
                    System.out.println("메뉴가 없습니다.");
                    continue;
                }
            }
        }
    }
}



