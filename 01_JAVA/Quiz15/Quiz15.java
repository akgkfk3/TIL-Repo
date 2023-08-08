package sesac.java.practice.day07.exam22;

import java.util.Scanner;

public class Quiz15 {
    public static void main(String[] args) throws InterruptedException {

        // 데이터 초기화
        Scanner sc = new Scanner(System.in);

        String[] skills = {"몸통박치기", "할퀴기", "울부짖기", "하이드로펌프",
                "물대포", "파도타기", "불대문자", "니트로차지", "솔라빔", "풀베기"};

        int[] skillPower = {60, 50, 20, 100, 90, 90, 120, 70, 110, 80};

        String[][] skillType = {
                {"물", "불", "풀"},
                {"불", "풀"},
                {"물", "불", "풀"},
                {"물"},
                {"물"},
                {"물"},
                {"불"},
                {"불"},
                {"풀"},
                {"풀"}
        };

        String[] pks = {"꼬북이", "파이리", "이상해씨", "뚜벅초", "고라파덕", "가디"};

        String[] pkType = {"물", "불", "풀", "풀", "물", "불"};

        int[][] pkStat = {
                {250, 90, 90},
                {150, 120, 90},
                {200, 100, 90},
                {150, 80, 80},
                {160, 80, 80},
                {150, 90, 100}
        };

        // PocketmonArchive 객체 생성 (위의 초기화된 데이터를 기반으로)
        PocketmonArchive pocketmonArchive = new PocketmonArchive(skills, skillPower, skillType,
                pks, pkType, pkStat);

        // 포켓몬은 랜덤으로 선택된다.
        System.out.println("파이리, 꼬북이, 이상해씨중 플레이어의 포켓몬을 뽑습니다.");
        System.out.println("이름을 입력해주세요.");

        // 포켓몬 이름 입력
        String pocketmonName = sc.nextLine();

        // PocketmonCenter 객체 생성 후, Pocket 객체 생성 + 데이터 출력
        PocketmonCenter pocketmonCenter = new PocketmonCenter(pocketmonArchive);
        PocketmonCenter taechoViliage = new TaechoViliage(pocketmonArchive);
        PocketmonCenter grassGym = new GrassGym(pocketmonArchive);
        
        // 포켓몬 생성 및 상태 출력
        Pocketmon pocketmon = pocketmonCenter.createPocketmon(pocketmonName);
        pocketmon.printStat();

        Quit: while(true) {
            System.out.println("무엇을 하시겠습니까?");
            System.out.println("1. 사냥\n" +
                               "2. 도전\n" +
                               "3. 치료\n" +
                               "4. 포켓몬 변경\n" +
                               "5. 종료");

            int menuNum = sc.nextInt();

            switch (menuNum) {
                case 1:
                    taechoViliage.fight(pocketmon, taechoViliage);
                    break;
                case 2:
                    grassGym.fight(pocketmon, grassGym);
                    break;
                case 3:
                    pocketmonCenter.heal(pocketmon);
                    break;
                case 4:
                    System.out.println("기능 개발중");
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    break Quit;
            }
        }
    }
}
