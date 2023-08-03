package sesac.java.practice.order;

import lombok.Getter;
import lombok.Setter;
import sesac.java.practice.menu.Menu;
import sesac.java.practice.order.inf.OnDelivery;

import java.util.ArrayList;

@Getter
@Setter
public class DeliveryOrder extends Order {

    private String locate;

    private OnDelivery onDelivery;

    public DeliveryOrder(ArrayList<Menu> orderList, OnDelivery onDelivery) {
        super(orderList);
        setOnDelivery(onDelivery);
    }

    @Override
    public boolean runOrder(int money) {
        if (money >= getOrderPrice()) {
            onDelivery.successDelivery(menuList, locate);
            return true;
        } else {
            return false;
        }
    }

    public void setOrderPrice(ArrayList<Menu> menuList) {
        for (Menu menu : menuList) {
                orderPrice += menu.getPrice();
        }
        orderPrice += 3000;
        System.out.println("총 금액은 배달비 3000원 추가되어 "
                + orderPrice + "원입니다.");
    }
}
