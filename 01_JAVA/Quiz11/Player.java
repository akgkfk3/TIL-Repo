package sesac.java.practice;

public class Player {

    public void play(Ladder ladder, int playerNum) {
        int[][] ld = ladder.getLadder();

        int pointer = playerNum;

        for(int i=0; i<ld.length; i++) {
            int idx = ld[i][pointer];

            if (idx == 1) {
                pointer += 1;
            }
            else if (idx == 2) {
                pointer -= 1;
            }
        }
        System.out.println((playerNum+1) + "번째 플레이어의 등수는 " + ladder.getRank()[pointer]);
    }
}
