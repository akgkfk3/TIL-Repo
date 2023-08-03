package sesac.java.practice.order;

import lombok.Getter;
import lombok.Setter;
import sesac.java.practice.menu.Menu;
import sesac.java.practice.order.inf.OnHere;

import java.util.ArrayList;

@Getter
@Setter
public class HereOrder extends Order {

    private int orderNum;

    private OnHere onHere;

    public HereOrder(ArrayList<Menu> menuList, OnHere onHere) {
        super(menuList);
        setOnHere(onHere);
    }

    @Override
    public boolean runOrder(int money) {
        if (money >= getOrderPrice()) {
            onHere.successHere(menuList, orderNum);
            return true;
        } else {
            return false;
        }
    }
}
