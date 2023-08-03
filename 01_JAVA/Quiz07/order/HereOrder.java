package sesac.java.practice.order;

import lombok.Getter;
import lombok.Setter;
import sesac.java.practice.order.inf.OnHere;

@Getter
@Setter
public class HereOrder extends Order {

    private int orderNum;

    private OnHere onHere;

    public HereOrder(String menu, int count, int Price, OnHere onHere) {
        super(menu, count, Price);
        setOnHere(onHere);
    }

    @Override
    public boolean runOrder(int money) {
        if (money >= getOrderPrice()) {
            onHere.successHere(menu, orderNum);
            return true;
        } else {
            return false;
        }
    }
}
