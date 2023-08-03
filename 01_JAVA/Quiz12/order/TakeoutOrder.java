package sesac.java.practice.order;

import lombok.Getter;
import lombok.Setter;
import sesac.java.practice.menu.Menu;
import sesac.java.practice.order.inf.OnTakeout;

import java.util.ArrayList;

@Getter
@Setter
public class TakeoutOrder extends Order {

    private int time;

    private OnTakeout onTakeout;

    public TakeoutOrder(ArrayList<Menu> orderList, OnTakeout onTakeout) {
        super(orderList);
        setOnTakeout(onTakeout);
    }

    @Override
    public boolean runOrder(int money) {
        if (money >= getOrderPrice()) {
            onTakeout.successTakeout(menuList, time);
            return true;
        } else {
            return false;
        }
    }

    public void setOrderPrice(ArrayList<Menu> menuList) {
        for (Menu menu : menuList) {
            if (menu != null)
                orderPrice += menu.getPrice();
        }
        orderPrice -= 500;
        System.out.println("총 금액은 테이크 아웃으로 500원 할인되어 "
                + orderPrice + "원입니다.");
    }
}
