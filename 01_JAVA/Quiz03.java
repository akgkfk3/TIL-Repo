package sesac.java.practice;

import java.io.IOException;
import java.util.Scanner;

public class Quiz03 {

    public static void main(String[] args) throws IOException {
        
        // Quiz_03
        for (int i=1; i<=19; i++) {
            if (i % 2 == 1)
                System.out.println("19 x " + i + " = " + (19*i));
        }
    }
}
