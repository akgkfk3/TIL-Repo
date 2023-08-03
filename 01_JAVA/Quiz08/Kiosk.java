package sesac.java.practice;

import sesac.java.practice.exception.KioskException;
import sesac.java.practice.order.DeliveryOrder;
import sesac.java.practice.order.HereOrder;
import sesac.java.practice.order.Order;
import sesac.java.practice.order.TakeoutOrder;
import sesac.java.practice.order.inf.OnDelivery;
import sesac.java.practice.order.inf.OnHere;
import sesac.java.practice.order.inf.OnTakeout;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Kiosk implements OnDelivery, OnHere, OnTakeout {

    private int inventory;

    private Map<String, Integer> menuMap = new ConcurrentHashMap<>();

    public Kiosk(int inventoryCount) {
        this.inventory = inventoryCount;
        menuMap.put("딸기요거트", 4500);
        menuMap.put("밀크티", 3500);
        menuMap.put("카페라떼", 4500);
        menuMap.put("아메리카노", 2000);
    }

    public Order initOrder(String orderMenu, int orderCount, int orderType, Kiosk kiosk) throws KioskException {

        Order order = null;

        // Kiosk 클래스

        // menu 및 재고 개수 Check
        if (!menuMap.containsKey(orderMenu)) {
            System.out.println("메뉴가 없습니다.");
            throw new KioskException(102);
        }
        else if (!isInventory(orderCount)) {
            System.out.println("재고가 부족합니다.");
            throw new KioskException(101);
        }

        switch (orderType) {
            case 1:
                OnDelivery onDelivery = kiosk;
                order = new DeliveryOrder(orderMenu, orderCount, menuMap.get(orderMenu), onDelivery);
                break;
            case 2:
                OnTakeout onTakeout = kiosk;
                order = new TakeoutOrder(orderMenu, orderCount, menuMap.get(orderMenu), onTakeout);
                break;
            case 3:
                OnHere onHere = kiosk;
                order = new HereOrder(orderMenu, orderCount, menuMap.get(orderMenu), onHere);
                break;
        }
        return order;
    }

    private boolean isInventory(int orderCount) {
        return (inventory >= orderCount) ? true : false;
    }

    public void subInventory(int orderCount) {
        inventory -= orderCount;
    }

    public void successTakeout(String menu, int time) {
        System.out.println(time + "분 뒤 " + menu + " 포장주문 완료되었습니다.");
    }

    public void successHere(String menu, int orderNum) {
        System.out.println(orderNum + " 주문번호로 " + menu + " 주문완료 되었습니다.");
    }

    public void successDelivery(String menu, String locate) {
        System.out.println(locate + " 주소로 " + menu + " 배달 주문이 완료되었습니다.");
    }
}
