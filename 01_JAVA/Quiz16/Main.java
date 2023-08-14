package ssac.emp.pss;

import java.util.Scanner;

public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        EmployeeMGR db = new EmployeeMGR();

        Quit : while(true) {
            System.out.println("-------------------------------");
            System.out.println("1. 회원 등록");
            System.out.println("2. 회원 목록");
            System.out.println("3. 회원 상세");
            System.out.println("4. 회원 수정");
            System.out.println("5. 회원 삭제");
            System.out.println("6. 종료");
            System.out.println("-------------------------------");
            System.out.print("번호를 입력해주세요 : ");
            int num = sc.nextInt();
            sc.nextLine();

            switch (num) {
                case 1:
                    db.insert();
                    break;
                case 2:
                    db.selectAll();
                    break;
                case 3:
                    db.select();
                    break;
                case 4:
                    db.update();
                    break;
                case 5:
                    db.delete();
                    break;
                case 6:
                    System.out.println("종료");
                    break Quit;
                default:
                    System.out.println("번호를 잘못 입력하셨습니다.");
            }
        }
    }
}
