package sesac.java.practice;

import lombok.Getter;
import lombok.Setter;
import sesac.java.practice.exception.KioskException;
import sesac.java.practice.menu.Menu;
import sesac.java.practice.order.DeliveryOrder;
import sesac.java.practice.order.HereOrder;
import sesac.java.practice.order.Order;
import sesac.java.practice.order.TakeoutOrder;
import sesac.java.practice.order.inf.OnDelivery;
import sesac.java.practice.order.inf.OnHere;
import sesac.java.practice.order.inf.OnTakeout;


@Getter
@Setter
public class Kiosk implements OnDelivery, OnHere, OnTakeout {

    public static final int key = 3;

    private int inventory;

    public Kiosk(int inventoryCount) {
        this.inventory = inventoryCount;
    }

    public Order initOrder(Menu[] orderList, int orderType, Kiosk kiosk) throws KioskException {

        Order order = null;

        int orderCount = 0;

        for (Menu menu : orderList)
            if (menu != null)
                orderCount++;

        // menu 및 재고 개수 Check
        if (!isInventory(orderCount)) {
            System.out.println("재고가 부족합니다.");
            throw new KioskException(101);
        }

        switch (orderType) {
            case 1:
                OnDelivery onDelivery = kiosk;
                order = new DeliveryOrder(orderList, onDelivery);
                break;
            case 2:
                OnTakeout onTakeout = kiosk;
                order = new TakeoutOrder(orderList, onTakeout);
                break;
            case 3:
                OnHere onHere = kiosk;
                order = new HereOrder(orderList,  onHere);
                break;
        }
        return order;
    }

    private boolean isInventory(int orderCount) {
        return (inventory >= orderCount) ? true : false;
    }

    public void subInventory(Menu[] orderList) {
        int orderCount = 0;

        for (Menu menu : orderList)
            if (menu != null)
                orderCount++;

        inventory -= orderCount;
    }

    public void successTakeout(Menu[] orderList, int time) {
        System.out.println(time + "분 뒤 " + " 포장주문 완료되었습니다.");
        printMenuList(orderList);
    }

    public void successHere(Menu[] orderList, int orderNum) {
        System.out.println(orderNum + " 주문번호로 " + " 주문완료 되었습니다.");
        printMenuList(orderList);
    }

    public void successDelivery(Menu[] orderList, String locate) {
        System.out.println(locate + " 주소로 " + " 배달 주문이 완료되었습니다.");
        printMenuList(orderList);
    }

    private void printMenuList(Menu[] orderList) {
        String orderMenu = "";

        for (Menu menu : orderList) {
            if (menu != null)
                orderMenu += menu.getMenu() + " ";
        }

        System.out.println("주문 내역 : " + orderMenu);
    }
}
