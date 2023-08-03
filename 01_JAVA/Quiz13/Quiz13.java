package sesac.java.practice;

import java.util.*;

public class Quiz13 {

    public static void main(String[] args) {

        // 블록 라인 개수 입력
        Scanner sc = new Scanner(System.in);
        System.out.println("블록이 들어갈 라인의 개수를 입력하세요.");
        int line = sc.nextInt();

        // 테트리스 객체 생성
        Tetris tetris = new Tetris(line);

        // 게임 횟수 (블록)
        int cnt = 1;
        Random rd = new Random();

        while(true) {
            int block = rd.nextInt(line * 2) + 1;

            System.out.println(block + " 블록이 들어갈 라인의 번호를 입력하세요.");
            int lineNum = sc.nextInt();

            if(!tetris.play(lineNum, block)) {
                System.out.println(cnt + "회 만에 " + lineNum + " 라인이 꽉 찼습니다.");
                tetris.print();
                break;
            }
            tetris.print();
            cnt++;
        }
    }
}

class Tetris {

    List<Stack<Integer>> list;

    Tetris(int line) {
        list = new LinkedList<>();

        for(int i=0; i<line; i++)
            list.add(new Stack<Integer>());
        print();
    }

    public boolean play(int lineNum, int block) {

        Stack stack = list.get(lineNum);

        // 해당 라인의 Stack이 비었을 경우
        if (stack.size() == 0) {
            stack.push(block);
        } else {
            // 해당 라인의 마지막 요소를 pop()
            int prevNum = (Integer) stack.pop();

            // 마지막 요소와 입력 받은 요소가 다른지 체크
            if (prevNum != block) {
                stack.push(prevNum);
                stack.push(block);
            }
        }
        if (stack.size() == 5)
            return false;

        return true;
    }

    public void print() {
        System.out.println("-------------------------");
        list.stream().forEach(x -> {
            x.stream().forEach(y -> {
                System.out.print(y.intValue() + " ");
            });
            System.out.println();
        });
        System.out.println("-------------------------");
    }
}
