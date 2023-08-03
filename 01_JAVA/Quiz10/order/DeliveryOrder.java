package sesac.java.practice.order;

import lombok.Getter;
import lombok.Setter;
import sesac.java.practice.menu.Menu;
import sesac.java.practice.order.inf.OnDelivery;

@Getter
@Setter
public class DeliveryOrder extends Order {

    private String locate;

    private OnDelivery onDelivery;

    public DeliveryOrder(Menu[] orderList, OnDelivery onDelivery) {
        super(orderList);
        setOnDelivery(onDelivery);
    }

    @Override
    public boolean runOrder(int money) {
        if (money >= getOrderPrice()) {
            onDelivery.successDelivery(orderList, locate);
            return true;
        } else {
            return false;
        }
    }

    public void setOrderPrice(Menu[] orderList) {
        for (Menu menu : orderList) {
            if (menu != null)
                orderPrice += menu.getPrice();
        }
        orderPrice += 3000;
        System.out.println("총 금액은 배달비 3000원 추가되어 "
                + orderPrice + "원입니다.");
    }
}
