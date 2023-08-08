package sesac.java.practice.day07.exam22;

public class GrassGym extends PocketmonCenter {

    // 출현 가능한 포켓몬 리스트
    private String[] pocketmonList = {"이상해씨"};

    public GrassGym(PocketmonArchive pocketmonArchive) {
        super(pocketmonArchive);
    }

    @Override
    protected String choicePocketmon() {
        return pocketmonList[rd.nextInt(1)];
    }

    @Override
    protected int createRandomInt(int num) {
        return num;
    }
}
