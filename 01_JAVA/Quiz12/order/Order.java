package sesac.java.practice.order;

import lombok.Getter;
import lombok.Setter;
import sesac.java.practice.menu.Menu;

import java.util.ArrayList;

@Getter
@Setter
public abstract class Order {
    protected ArrayList<Menu> menuList;

    protected int count;

    protected int orderPrice;

    public Order(ArrayList<Menu> menuList) {
        this.menuList = menuList;
        this.count = menuList.size();
        setOrderPrice(menuList);
    }

    public void setOrderPrice(ArrayList<Menu> menuList) {
        for (Menu menu : menuList) {
                orderPrice += menu.getPrice();
        }
        System.out.println("총 금액은 "+ orderPrice + "원입니다.");
    }

    public abstract boolean runOrder(int money);
}
