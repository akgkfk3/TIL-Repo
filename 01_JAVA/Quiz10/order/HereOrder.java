package sesac.java.practice.order;

import lombok.Getter;
import lombok.Setter;
import sesac.java.practice.menu.Menu;
import sesac.java.practice.order.inf.OnHere;

@Getter
@Setter
public class HereOrder extends Order {

    private int orderNum;

    private OnHere onHere;

    public HereOrder(Menu[] orderList, OnHere onHere) {
        super(orderList);
        setOnHere(onHere);
    }

    @Override
    public boolean runOrder(int money) {
        if (money >= getOrderPrice()) {
            onHere.successHere(orderList, orderNum);
            return true;
        } else {
            return false;
        }
    }
}
