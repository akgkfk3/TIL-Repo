package sesac.java.practice;

import lombok.Getter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
public class LottoBot {

    private final List<Set<Integer>> userLottoList;

    public LottoBot(int count) {

        userLottoList = new ArrayList<>();

        for(int i=0; i<count; i++) {
            Set<Integer> userLotto = new HashSet<>();

            while (userLotto.size() < 6)
                userLotto.add(StaticClass.rd.nextInt(46));

            userLottoList.add(userLotto);
        }
    }
}
