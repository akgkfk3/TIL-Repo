package sesac.java.practice;

import lombok.Getter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Getter
public class Lotto {

    private final Set<Integer> lotto;

    public Lotto() {
        this.lotto = new HashSet<>();

        while (lotto.size() < 6) {
            lotto.add(StaticClass.rd.nextInt(46));
        }
    }

    public void checkLotto(LottoBot lottoBot) {

        List<Set<Integer>> userLottoList = lottoBot.getUserLottoList();

        Iterator<Set<Integer>> it = userLottoList.iterator();

        while(it.hasNext()) {
            Set<Integer> userLotto = it.next();

            userLotto.retainAll(lotto);

            switch(userLotto.size()) {
                case 6:
                    System.out.println("1등");
                    break;
                case 5:
                    System.out.println("2등");
                    break;
                case 4:
                    System.out.println("3등");
                    break;
                case 3:
                    System.out.println("4등");
                    break;
                default:
                    System.out.println("꽝");
                    break;
            }
        }
    }
}
