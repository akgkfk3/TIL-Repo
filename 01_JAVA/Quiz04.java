package sesac.java.practice;

import java.io.IOException;
import java.util.Random;

public class Quiz04 {

    public static void main(String[] args) throws IOException {
        // Quiz_04
        
        Random rd = new Random();

        for (int i=1; i<6; i++) {
            System.out.print(i + " 번째 로또 뽑기 : ");
            for (int j=0; j<5; j++)
                System.out.print(rd.nextInt(46) + " ");
            System.out.println();
        }
    }
}
