package sesac.java.practice.day07.exam22;

public class TaechoViliage extends PocketmonCenter {

    // 출현 가능한 포켓몬 리스트
    private String[] pocketmonList = {"뚜벅초", "고라파덕", "가디"};

    public TaechoViliage(PocketmonArchive pocketmonArchive) {
        super(pocketmonArchive);
    }

    @Override
    protected int createRandomInt(int num) {
        int check = rd.nextInt(2);

        if (check == 1) {
            num += rd.nextInt(6);
        } else {
            num -= rd.nextInt(6);
        }
        return num;
    }
}
