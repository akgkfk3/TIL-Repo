package ssac.emp.pss;

import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static ssac.emp.pss.Main.*;

public class EmployeeMGR {

    private volatile static int num = 1;

    private Map<String, Employee> db = new ConcurrentHashMap<>();

    synchronized public void insert() {
        System.out.println("-------------------------------");
        System.out.println("회원 등록 폼");
        System.out.println("-------------------------------");

        // 회원 사번
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMdd");
        String sabun = simpleDateFormat.format(date) + String.format("%04d", num);

        // 회원 이름
        System.out.println("이름을 입력해주세요.");
        String name = sc.nextLine();

        // 회원 휴대폰번호
        System.out.println("휴대폰 번호를 입력해주세요. ex) 010-xxxx-xxxx");
        String phone = sc.nextLine();

        // 회원 주소
        System.out.println("주소를 입력해주세요.");
        String addr = sc.nextLine();

        // Employee 객체 생성
        Employee employee = new Employee();
        employee.setSabun(sabun);
        employee.setName(name);
        employee.setPhone(phone);
        employee.setAddr(addr);

        // 회원 등록
        if(db.put(sabun, employee) == null) {
            System.out.println("회원 등록 완료");
            num++;
        } else {
            System.out.println("회원 등록 실패");
        }
    }

    synchronized public void delete() {
        System.out.println("-------------------------------");
        System.out.println("회원 삭제 폼");
        System.out.println("-------------------------------");
        System.out.println("삭제할 회원 사번을 입력해주세요.");
        String sabun = sc.nextLine();

        if(db.containsKey(sabun)) {
            db.remove(sabun);
        } else {
            System.out.println("등록되지 않은 사번입니다.");
        }
    }

    synchronized public void update() {
        System.out.println("-------------------------------");
        System.out.println("회원 수정 폼");
        System.out.println("-------------------------------");
        System.out.println("수정할 회원 사번을 입력해주세요.");
        String sabun = sc.nextLine();

        if(db.containsKey(sabun)) {
            Employee employee = db.get(sabun);

            // 회원 휴대폰번호
            System.out.println("휴대폰 번호를 입력해주세요. ex) 010-xxxx-xxxx");
            String phone = sc.nextLine();

            // 회원 주소
            System.out.println("주소를 입력해주세요.");
            String addr = sc.nextLine();

            // 업데이트
            employee.setPhone(phone);
            employee.setAddr(addr);

        } else {
            System.out.println("등록되지 않은 사번입니다.");
        }
    }

    public void select() {
        System.out.println("-------------------------------");
        System.out.println("회원 상세 폼");
        System.out.println("-------------------------------");
        System.out.println("회원 사번을 입력해주세요.");
        String sabun = sc.nextLine();

        if(db.containsKey(sabun)) {
            System.out.println(db.get(sabun).toString());
        } else {
            System.out.println("등록되지 않은 사번입니다.");
        }
    }

    public void selectAll() {
        System.out.println("-------------------------------");
        System.out.println("회원 목록 폼");
        System.out.println("-------------------------------");

        // 등록된 회원이 없는 경우
        if(db.size() == 0) {
            System.out.println("등록된 회원이 없습니다.");
            return;
        }

        System.out.println("회원사번\t\t\t\t\t회원이름");

        db.keySet().stream().forEach(x -> {
            System.out.println(x + "\t\t\t\t" + db.get(x).getName());
        });
    }
}
