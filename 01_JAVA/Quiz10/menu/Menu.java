package sesac.java.practice.menu;

import lombok.Getter;
import lombok.Setter;
import sesac.java.practice.exception.KioskException;

@Getter
@Setter
public class Menu {

    private String menu;

    private int price;

    public Menu(String menu) throws KioskException {
        this.menu = menu;
        setPrice(menu);
    }

    public void setPrice(String menu) throws KioskException {
        if (menu.equals("딸기요거트") || menu.equals("카페라떼")) {
            price = 4500;
        }
        else if (menu.equals("밀크티")) {
            price = 3500;
        }
        else if (menu.equals("아메리카노")) {
            price = 2000;
        }
        else {
            throw new KioskException(102);
        }
    }
}
