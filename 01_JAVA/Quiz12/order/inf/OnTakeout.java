package sesac.java.practice.order.inf;

import sesac.java.practice.menu.Menu;

import java.util.ArrayList;

public interface OnTakeout {

    void successTakeout(ArrayList<Menu> menuList, int time);
}
