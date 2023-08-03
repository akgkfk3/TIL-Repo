package sesac.java.practice.order.inf;

import sesac.java.practice.menu.Menu;

import java.util.ArrayList;

public interface OnDelivery {

    void successDelivery(ArrayList<Menu> menuList, String locate);
}
