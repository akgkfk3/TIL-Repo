package sesac.java.practice;

import java.util.Scanner;

public class Exam18 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("로또를 몇 개 생성하시겠습니까?");
        int lottoCount = sc.nextInt();

        // LottoBot 객체 생성
        LottoBot lottoBot = new LottoBot(lottoCount);
        
        // Lotto 객체 생성
        Lotto lotto = new Lotto();

        // Lotto 체크
        lotto.checkLotto(lottoBot);
    }
}
