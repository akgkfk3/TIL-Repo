package sesac.java.practice.day07.exam22;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Pocketmon {

    private String name;

    private int hp;

    private Map<String, String> stat;

    private Map<String, Integer> skill;

    public Pocketmon() {}

    public void printStat() {
        System.out.println(name + "의 상태입니다.");
        System.out.println("현재 체력 : " + hp);
        System.out.print("능력창 " + stat.toString());
        System.out.println();
        System.out.println("스킬창 " + skill.toString());
    }

}
