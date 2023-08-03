package sesac.java.practice.order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Order {
    protected String menu;

    protected int count;

    protected int orderPrice;

    public Order(String menu, int count, int price) {
        this.menu = menu;
        this.count = count;
        setOrderPrice(price);
    }

    public void setOrderPrice(int price) {
        this.orderPrice = count * price;
        System.out.println("총 금액은 "+ orderPrice + "원입니다.");
    }

    public abstract boolean runOrder(int money);
}
