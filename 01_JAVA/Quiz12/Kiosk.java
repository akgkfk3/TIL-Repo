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

import java.util.ArrayList;


@Getter
@Setter
public class Kiosk implements OnDelivery, OnHere, OnTakeout {

    public static final int key = 3;

    private int inventory;

    public Kiosk(int inventoryCount) {
        this.inventory = inventoryCount;
    }

    public Order initOrder(ArrayList<Menu> menuList, int orderType, Kiosk kiosk) throws KioskException {

        Order order = null;

        // menu 및 재고 개수 Check
        if (!isInventory(menuList.size())) {
            System.out.println("재고가 부족합니다.");
            throw new KioskException(101);
        }

        switch (orderType) {
            case 1:
                OnDelivery onDelivery = kiosk;
                order = new DeliveryOrder(menuList, onDelivery);
                break;
            case 2:
                OnTakeout onTakeout = kiosk;
                order = new TakeoutOrder(menuList, onTakeout);
                break;
            case 3:
                OnHere onHere = kiosk;
                order = new HereOrder(menuList,  onHere);
                break;
        }
        return order;
    }

    private boolean isInventory(int orderCount) {
        return (inventory >= orderCount) ? true : false;
    }

    public void subInventory(ArrayList<Menu> orderList) {
        inventory -= orderList.size();
    }

    public void successTakeout(ArrayList<Menu> menuList, int time) {
        System.out.println(time + "분 뒤 " + " 포장주문 완료되었습니다.");
        printMenuList(menuList);
    }

    public void successHere(ArrayList<Menu> menuList, int orderNum) {
        System.out.println(orderNum + " 주문번호로 " + " 주문완료 되었습니다.");
        printMenuList(menuList);
    }

    public void successDelivery(ArrayList<Menu> menuList, String locate) {
        System.out.println(locate + " 주소로 " + " 배달 주문이 완료되었습니다.");
        printMenuList(menuList);
    }

    private void printMenuList(ArrayList<Menu> menuList) {
        String orderMenu = "";

        for (Menu menu : menuList) {
                orderMenu += menu.getMenu() + " ";
        }
        System.out.println("주문 내역 : " + orderMenu);
    }
}
