package sesac.java.practice.order;

import lombok.Getter;
import lombok.Setter;
import sesac.java.practice.menu.Menu;
import sesac.java.practice.order.inf.OnTakeout;

@Getter
@Setter
public class TakeoutOrder extends Order {

    private int time;

    private OnTakeout onTakeout;

    public TakeoutOrder(Menu[] orderList, OnTakeout onTakeout) {
        super(orderList);
        setOnTakeout(onTakeout);
    }

    @Override
    public boolean runOrder(int money) {
        if (money >= getOrderPrice()) {
            onTakeout.successTakeout(orderList, time);
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
        orderPrice -= 500;
        System.out.println("총 금액은 테이크 아웃으로 500원 할인되어 "
                + orderPrice + "원입니다.");
    }
}
