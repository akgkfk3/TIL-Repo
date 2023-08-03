package sesac.java.practice;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Kiosk {

    // 키오스크 키
    public static final int key = 3;
    
    // 재고 개수
    private int inventoryCount;

    private Map<String, Integer> menuMap = new ConcurrentHashMap<>();

    public Kiosk(int inventoryCount) {
        this.inventoryCount = inventoryCount;
        menuMap.put("딸기요거트", 4500);
        menuMap.put("밀크티", 3500);
        menuMap.put("카페라떼", 4500);
        menuMap.put("아메리카노", 2000);
    }

    // 재고개수가 충분한 지 체크
    public boolean isInventory(int orderCount) {
        return (inventoryCount >= orderCount) ? true : false;
    }

    // 재고 개수에서 주문 개수 마이너스
    public void subInventroy(int orderCount) {
        inventoryCount -= orderCount;
    }

    // 메뉴가 있으면 Order 객체 Return 아니면 Null
    public Order initOrder(String orderMenu, int orderCount) {
        if (menuMap.containsKey(orderMenu)) {
            return new Order(orderMenu, orderCount, menuMap.get(orderMenu));
        }
        return null;
    }
}
