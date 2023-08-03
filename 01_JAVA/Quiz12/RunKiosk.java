package sesac.java.practice;

import sesac.java.practice.exception.KioskException;
import sesac.java.practice.menu.Menu;
import sesac.java.practice.order.DeliveryOrder;
import sesac.java.practice.order.HereOrder;
import sesac.java.practice.order.Order;
import sesac.java.practice.order.TakeoutOrder;

import java.util.ArrayList;
import java.util.Scanner;

public class RunKiosk {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // 키오스크 키 입력
        System.out.println("키오스크 키를 입력해주세요.");
        int key = sc.nextInt();

        if (key == Kiosk.key) {

            // 재고 개수 입력
            System.out.println("재고개수를 입력해주세요.");
            int inventory = sc.nextInt();

            // Kiosk 객체 생성
            Kiosk kiosk = new Kiosk(inventory);

            while (true) {
                System.out.println("1. 배달 \n" +
                        "2. 포장 \n" +
                        "3. 매장 \n" +
                        "4. 종료 \n" +
                        "주문 방식 번호를 입력해주세요.");

                // 주문 방식 선택
                int orderType = sc.nextInt();

                // 4번 선택시, Break로 Loop 탈출
                if (orderType == 4) {
                    System.out.println("종료되었습니다.");
                    break;
                }

                // Menu 배열 생성 (최대 10개)
                ArrayList<Menu> menuList = new ArrayList<>();

                // 메뉴 입력
                for(int i=0; i<10; i++) {

                    Menu menu = null;

                    System.out.println("메뉴 또는 주문을 입력해주세요.");
                    String orderMenu = sc.next();

                    if (orderMenu.equals("주문"))
                        break;

                    try {
                        menu = new Menu(orderMenu);
                    } catch (KioskException e) {
                        System.out.println("입력하신 메뉴는 없는 메뉴입니다.");
                        System.out.println("메뉴를 다시 입력해주세요.");
                        i--;
                        continue;
                    }
                    menuList.add(menu);
                }

                Order order = null;

                try {
                    order = kiosk.initOrder(menuList, orderType, kiosk);
                } catch (KioskException e) {
                    // 주문이 제대로 안되었을 때 (재고가 부족)
                    System.out.println(e.getMessage());
                    continue;
                }

                // 지불금액 입력 + 버퍼 개행문자 제거
                System.out.println("지불할 금액을 입력해주세요.");
                int money = sc.nextInt();
                sc.nextLine();

                // 주문방식에 따른 추가정보 입력
                switch (orderType) {
                    case 1:
                        // 주소 입력
                        System.out.println("주소를 입력해주세요.");
                        String locate = sc.nextLine();
                        ((DeliveryOrder) order).setLocate(locate);
                        break;
                    case 2:
                        // 포장시간 입력
                        System.out.println("포장시간을 입력해주세요.");
                        int time = sc.nextInt();
                        ((TakeoutOrder) order).setTime(time);
                        break;
                    case 3:
                        // 주문번호 입력
                        System.out.println("주문번호를 입력해주세요.");
                        int orderNum = sc.nextInt();
                        ((HereOrder) order).setOrderNum(orderNum);
                        break;
                }
                // 지불금액이 주문금액에 비해 부족한 지 체크
                if(order.runOrder(money)) {
                    kiosk.subInventory(menuList);
                    System.out.println();
                } else {
                    System.out.println("금액이 부족합니다.");
                }
            }

        } else {
            System.out.println("키오스크 키가 틀렸습니다.");
        }
    }
}
