package sesac.java.practice;

import sesac.java.practice.exception.KioskException;
import sesac.java.practice.order.DeliveryOrder;
import sesac.java.practice.order.HereOrder;
import sesac.java.practice.order.Order;
import sesac.java.practice.order.TakeoutOrder;

import java.util.Scanner;

public class RunKiosk {
    public static void main(String[] args) {

        // 재고개수 입력
        Scanner sc = new Scanner(System.in);
        System.out.println("재고개수를 입력해주세요.");
        int inventory = sc.nextInt();

        // Kiosk 객체 생성
        Kiosk kiosk = new Kiosk(inventory);

        while (true) {
            System.out.println("1. 배달 \n" +
                               "2. 포장 \n" +
                               "3. 매장 \n" +
                               "4. 종료 \n" +
                               "주문 방식을 번호를 입력해주세요.");
            // 주문 방식 선택
            int orderType = sc.nextInt();

            // 4번 선택시, Break로 Loop 탈출
            if (orderType == 4) {
                System.out.println("종료되었습니다.");
                break;
            }

            // 메뉴 및 개수 입력
            System.out.println("메뉴와 개수를 입력해주세요.");
            String orderMenu = sc.next();
            int orderCount = sc.nextInt();

            Order order = null;

            // Runkiosk 클래스

            try {
                order = kiosk.initOrder(orderMenu, orderCount, orderType, kiosk);
            } catch (KioskException e) {
                System.out.println(e.getMessage());
            }

            // 주문이 제대로 안되었을 때 (메뉴가 없거나, 재고가 없거나)
            if (order == null) continue;

            // 지불금액 입력 + 버퍼 개행문자 제거
            System.out.println("지불할 금액을 입력해주세요.");
            int money = sc.nextInt();
            sc.nextLine();

            // 지불금액이 주문금액에 비해 부족한 지 체크
            if(!order.runOrder(money)) {
                System.out.println("금액이 부족합니다.");
                continue;
            }

            // 주문방식에 따른 추가정보 입력
            switch(orderType) {
                case 1:
                    // 주소 입력
                    System.out.println("주소를 입력해주세요.");
                    String locate = sc.nextLine();

                    DeliveryOrder deliveryOrder = (DeliveryOrder) order;
                    deliveryOrder.setLocate(locate);

                    deliveryOrder.getOnDelivery().successDelivery(
                            deliveryOrder.getMenu(), deliveryOrder.getLocate());
                    break;
                case 2:
                    // 포장시간 입력
                    System.out.println("포장시간을 입력해주세요.");
                    int time = sc.nextInt();

                    TakeoutOrder takeoutOrder = (TakeoutOrder) order;
                    takeoutOrder.setTime(time);

                    takeoutOrder.getOnTakeout().successTakeout(
                            takeoutOrder.getMenu(), takeoutOrder.getTime()
                    );
                    break;
                case 3:
                    // 주문번호 입력
                    System.out.println("주문번호를 입력해주세요.");
                    int orderNum = sc.nextInt();

                    HereOrder hereOrder = (HereOrder) order;
                    hereOrder.setOrderNum(orderNum);

                    hereOrder.getOnHere().successHere(
                            hereOrder.getMenu(), hereOrder.getOrderNum());
                    break;
            }
            
            // 주문 성공 시, 재고 개수 마이너스
            kiosk.subInventory(orderCount);
        }
    }
}
