package sesac.java.practice.order;

import lombok.Getter;
import lombok.Setter;
import sesac.java.practice.order.inf.OnDelivery;

@Getter
@Setter
public class DeliveryOrder extends Order {

    private String locate;

    private OnDelivery onDelivery;

    public DeliveryOrder(String menu, int count, int Price, OnDelivery onDelivery) {
        super(menu, count, Price);
        setOnDelivery(onDelivery);
    }

    @Override
    public boolean runOrder(int money) {
        if (money >= getOrderPrice()) {
            onDelivery.successDelivery(menu, locate);
            return true;
        } else {
            return false;
        }
    }

    public void setOrderPrice(int price) {
        orderPrice = count * price + 3000;
        System.out.println("총 금액은 배달비 3000원 추가되어 "
                + orderPrice + "원입니다.");
    }
}
