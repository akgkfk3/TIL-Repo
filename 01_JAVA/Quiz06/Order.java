package sesac.java.practice;

public class Order {
    
    // 주문 메뉴
    private String orderMenu;
    
    // 주문 개수
    private int orderCount;

    // 메뉴 가격
    private int orderPrice;

    public Order(String orderMenu, int orderCount, int menuPrice) {
        this.orderMenu = orderMenu;
        this.orderCount = orderCount;
        setOrderPrice(menuPrice);
    }

    private void setOrderPrice(int menuPrice) {
        orderPrice = orderCount * menuPrice;
    }

    public int getOrderPrice() {
        return orderPrice;
    }

    public boolean runOrder(int money) {
        if (money > orderPrice) {
            System.out.println("잔돈 " + (money-orderPrice) +
                    "와 " + orderMenu + " 나왔습니다.");
            return true;
        } else {
            System.out.println("금액이 부족합니다.");
            return false;
        }
    }
}
