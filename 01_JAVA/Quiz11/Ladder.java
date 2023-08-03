package sesac.java.practice;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Random;

@Getter
@Setter
public class Ladder {

    private int[][] ladder;

    private int[] rank;

    public Ladder(int[][] ladder, int[] rank) {
        setRank(rank);
        setLadder(ladder);
        printLadder();
    }

    /**
     * 사다리 초기화 메소드
     * @param ladder
     */
    private void setLadder(int[][] ladder) {
        Random rd = new Random();

        for(int i=0; i<ladder.length; i++) {
            int idx = rd.nextInt(ladder[i].length);

            if (idx == ladder[i].length - 1) {
                ladder[i][idx-1] = 1;
                ladder[i][idx] = 2;
                continue;
            }
            ladder[i][idx] = 1;
            ladder[i][idx+1] = 2;
        }
        this.ladder = ladder;
    }

    /**
     * 사다리 및 등수 출력하는 메소드
     */
    private void printLadder() {
        System.out.println("----------사다리----------");
        Arrays.stream(ladder).forEach(
                x -> { Arrays.stream(x).forEach(
                            y -> System.out.print(Integer.valueOf(y)+ "\t"));
                       System.out.println();
                }
        );
        System.out.println("----------등 수----------");
        Arrays.stream(rank).forEach(x -> System.out.print(x + "\t"));
        System.out.println();
    }
}
