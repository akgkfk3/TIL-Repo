package sesac.java.practice.order;

import lombok.Getter;
import lombok.Setter;
import sesac.java.practice.menu.Menu;

@Getter
@Setter
public abstract class Order {
    protected Menu[] orderList;

    protected int count;

    protected int orderPrice;

    public Order(Menu[] orderList) {
        this.orderList = orderList;
        this.count = orderList.length;
        setOrderPrice(orderList);
    }

    public void setOrderPrice(Menu[] orderList) {
        for (Menu menu : orderList) {
            if (menu != null)
                orderPrice += menu.getPrice();
        }
        System.out.println("총 금액은 "+ orderPrice + "원입니다.");
    }

    public abstract boolean runOrder(int money);
}
